package com.example.bookmyshow.model;

public class ConfirmedState implements BookingState{
    @Override
    public void lockSeats(Booking booking) {
        throw new IllegalStateException("Seats already locked");
    }

    @Override
    public void confirm(Booking booking) {
        throw new IllegalStateException("Seats already confirmed");
    }

    @Override
    public void cancel(Booking booking) {
        booking.getSeats().forEach(ShowSeat::release);
        booking.setState(new CancelledState(), BookingStatus.CANCELLED);
    }

    @Override
    public void expire(Booking booking) {
        throw new IllegalStateException("Confirmed Seats cant be expired");
    }

    @Override
    public void fail(Booking booking) {
        throw new IllegalStateException("Confirmed Seats cant be failed");
    }
}
