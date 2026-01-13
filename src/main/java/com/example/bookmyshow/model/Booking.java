package com.example.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Getter
public class Booking {

    // 🔑 Primary key for booking
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 👤 Who made the booking
    // Many bookings can belong to one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 🎬 Which show this booking is for
    // Many bookings can exist for one show
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    /*
     🎟 Seats booked in this booking
     - One booking → many ShowSeats
     - A ShowSeat belongs to only one booking (once booked)
     - orphanRemoval ensures cleanup if booking is deleted
    */
    @OneToMany
    @JoinColumn(name = "booking_id")
    private List<ShowSeat> showSeats;

    // 💰 Total amount paid for this booking
    private Double totalAmount;

    // 🔄 Booking lifecycle status
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    // 🕒 When booking was created
    private LocalDateTime createdAt;

    // 🕒 When booking was cancelled (nullable)
    private LocalDateTime cancelledAt;

    // 🔒 JPA requires a no-arg constructor
    protected Booking() {}

    /*
     ✅ Controlled constructor
     - Forces correct creation
     - Prevents partially-created bookings
    */
    public Booking(User user, Show show, List<ShowSeat> showSeats, Double totalAmount) {
        this.user = user;
        this.show = show;
        this.showSeats = showSeats;
        this.totalAmount = totalAmount;
        this.status = BookingStatus.CREATED;
        this.createdAt = LocalDateTime.now();
    }

    // ✅ Business method (instead of setter)
    public void confirm() {
        this.status = BookingStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
        this.cancelledAt = LocalDateTime.now();
    }
}
