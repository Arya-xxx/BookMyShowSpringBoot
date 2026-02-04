package com.example.bookmyshow.model;

public class CancelledState implements BookingState{
    @Override
    public void lockSeats(Booking booking) {
        throw new IllegalStateException("Booking cancelled");
    }

    @Override
    public void confirm(Booking booking) {
        throw new IllegalStateException("Booking cancelled");
    }

    @Override
    public void cancel(Booking booking) {
        throw new IllegalStateException("Booking cancelled");
    }

    @Override
    public void expire(Booking booking) {
        throw new IllegalStateException("Booking cancelled");
    }

    @Override
    public void fail(Booking booking) {
        throw new IllegalStateException("Booking cancelled");

    }

    @Override
    public void release(Booking booking) {
        throw new IllegalStateException("Booking cancelled");
    }
}
