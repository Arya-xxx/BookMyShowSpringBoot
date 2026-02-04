package com.example.bookmyshow.model;

public class ReleasedState implements BookingState {

    @Override
    public void lockSeats(Booking booking) {
        throw new IllegalStateException("Seats already released");
    }

    @Override
    public void confirm(Booking booking) {
        throw new IllegalStateException("Cannot confirm after release");
    }

    @Override
    public void cancel(Booking booking) {
        // no-op
    }

    @Override
    public void expire(Booking booking) {
        // already expired/released
    }

    @Override
    public void fail(Booking booking) {
        // already failed/released
    }

    @Override
    public void release(Booking booking) {
        // idempotent – already released
    }

    /*
     🎯 CORE RESPONSIBILITY:
     - Release all seats
     - This happens ONCE
    */
    public static void releaseSeats(Booking booking) {
        booking.getSeats().forEach(ShowSeat::release);
    }
}
