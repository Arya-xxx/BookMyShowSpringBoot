package com.example.bookmyshow.model;

public class ExpiredState implements BookingState{
    @Override
    public void lockSeats(Booking booking) {
        throw new IllegalStateException("Booking expired");
    }

    @Override
    public void confirm(Booking booking) {
        throw new IllegalStateException("Booking expired");
    }

    @Override
    public void cancel(Booking booking) {
        throw new IllegalStateException("Booking expired");
    }

    @Override
    public void expire(Booking booking) {

    }

    @Override
    public void fail(Booking booking) {

    }
}
