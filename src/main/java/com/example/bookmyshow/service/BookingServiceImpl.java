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


    @Override
    @Transactional
    public BookingResponseDto createBooking(Long userId, Long showId, List<Long> showSeatIds) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found"));
        Show show =showRepository.findById(showId).orElseThrow(()->new RuntimeException("Show not found"));
        List<ShowSeat>seats = showSeatRepository.findAvailableSeatsForUpdate(showSeatIds);

        Booking booking = new Booking(user, show, seats, calculatePrice(seats));
        booking.lockSeats();
        booking.confirm();
        Booking savedBooking = bookingRepository.save(booking);


        return new BookingResponseDto(savedBooking.getId(), savedBooking.getStatus());

    }


    private double calculatePrice(List<ShowSeat> seats) {
        return seats.stream()
                .mapToDouble(ShowSeat::getPrice)
                .sum();
    }
}

