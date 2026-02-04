package com.example.bookmyshow.service;

import com.example.bookmyshow.dto.BookingResponseDto;
import com.example.bookmyshow.model.Booking;
import com.example.bookmyshow.model.Show;
import com.example.bookmyshow.model.ShowSeat;
import com.example.bookmyshow.model.User;
import com.example.bookmyshow.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl  implements  BookingService{

    private final UserRepository userRepository;
    private final SeatRepository seatRepository;
    private final ShowSeatRepository showSeatRepository;
    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final PricingStrategyFactory pricingStrategyFactory;
    private final PaymentService paymentService;
    @Override
    @Transactional
    public BookingResponseDto createBooking(Long userId, Long showId, List<Long> showSeatIds) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found"));
        Show show =showRepository.findById(showId).orElseThrow(()->new RuntimeException("Show not found"));
        List<ShowSeat>seats = showSeatRepository.findAvailableSeatsForUpdate(showSeatIds);

        PricingStrategy pricingStrategy = pricingStrategyFactory.getStrategy(show);



        double total = pricingStrategy.calculatePrice(seats);


        Booking booking = new Booking(user, show, seats, calculatePrice(seats));
        booking.lockSeats();
        booking.confirm();
        booking.assignPrice(total);
        Booking savedBooking = bookingRepository.save(booking);


        paymentService.processPaymentAsync(booking.getId(), booking.getTotalAmount())
                .thenAccept(success -> handlePaymentResult(booking.getId(), success));


        return new BookingResponseDto(savedBooking.getId(), savedBooking.getStatus());

    }


    private double calculatePrice(List<ShowSeat> seats) {
        return seats.stream()
                .mapToDouble(ShowSeat::getPrice)
                .sum();
    }


    @Transactional
    public void handlePaymentResult(Long bookingId, boolean success) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow();

        if (success) {
            booking.confirm();
        } else {
            booking.fail();
            booking.release();
        }

        bookingRepository.save(booking);
    }



}
/*

User
 |
 |  POST /book
 v
BookingController
 |
 |  createBooking()
 v
BookingService
 |
 |-- find User ---------------------> UserRepository
 |<----------------------------------
 |
 |-- find Show ---------------------> ShowRepository
 |<----------------------------------
 |
 |-- lock seats (SELECT FOR UPDATE)-> ShowSeatRepository
 |<----------------------------------
 |
 |-- save Booking (CREATED) --------> BookingRepository
 |<----------------------------------
 |
 |-- handlePayment(bookingId)
 |----------------------------------------------+
 |                                              |
 |                                     (async boundary)
 |                                              |
 |                               PaymentService.processPaymentAsync()
 |                                              |
 |                                ExecutorService (bookingExecutor)
 |                                              |
 |                              [New Thread from Pool]
 |                                              |
 |                         CompletableFuture.supplyAsync()
 |                                              |
 |                      (sleep / gateway / random result)
 |                                              |
 |                      ------------------------+
 |                      | whenComplete callback |
 |                      v
 |              handlePaymentResult(bookingId)
 |                      |
 |-- find Booking ------------------> BookingRepository
 |<----------------------------------
 |
 |-- [if success] confirm booking
 |-- [if failure] fail booking + release seats
 |
 |-- save Booking ------------------> BookingRepository
 |<----------------------------------
 |
 v
User (later sees final status)

 */

