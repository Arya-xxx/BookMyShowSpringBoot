package com.example.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "show_seats")
@Getter
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Seat number inside a show
     */
    private Long seatNumber;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    public SeatType getSeatType() {
        return seat.getSeatType();
    }

    private double price;

    /**
     * Seat status (AVAILABLE / LOCKED / BOOKED)
     */
    @Enumerated(EnumType.STRING)
    private ShowSeatStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;
    /**
     * ===============================
     * OWNING SIDE OF RELATIONSHIP
     * ===============================
     *
     * This table contains:
     *   show_id (FK)
     *
     * Hence:
     * • @JoinColumn lives HERE
     * • This is the owning side
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    protected ShowSeat() {}

    public ShowSeat(Long seatNumber) {
        this.seatNumber = seatNumber;
        this.status = ShowSeatStatus.AVAILABLE;
    }

    /**
     * Package-private method
     * Only Show aggregate can assign parent
     */
    void assignShow(Show show) {
        this.show = show;
    }

    public void lock(){
        if(this.status!=ShowSeatStatus.AVAILABLE){
            throw new IllegalStateException("Seat Not Available");
        }
        this.status = ShowSeatStatus.LOCKED;
    }

    public  void release(){
        this.status = ShowSeatStatus.AVAILABLE;
        this.booking = null;
    }


    public void book() {
        if (this.status != ShowSeatStatus.AVAILABLE) {
            throw new IllegalStateException("Seat is already booked");
        }
        this.status = ShowSeatStatus.BOOKED;
    }

    /**
     * Release seat (used in cancellation / payment failure)
     */
}
