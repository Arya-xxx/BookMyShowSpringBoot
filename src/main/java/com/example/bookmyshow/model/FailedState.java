package com.example.bookmyshow.model;

public class FailedState implements BookingState{
    @Override
    public void lockSeats(Booking booking) {

    }

    @Override
    public void confirm(Booking booking) {
        throw new IllegalStateException("Payment failed");
    }

    @Override
    public void cancel(Booking booking) {

    }

    @Override
    public void expire(Booking booking) {

    }

    @Override
    public void fail(Booking booking) {

    }
}
