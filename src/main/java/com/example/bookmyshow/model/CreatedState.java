package com.example.bookmyshow.model;

public class CreatedState implements BookingState{
    @Override
    public void lockSeats(Booking booking) {
        booking.getShowSeats().forEach(ShowSeat::lock);
    }

    @Override
    public void confirm(Booking booking) {
        booking.setState(new ConfirmedState(), BookingStatus.CONFIRMED);
    }

    @Override
    public void cancel(Booking booking) {
        booking.getShowSeats().forEach(ShowSeat::release);
        booking.setState(new CancelledState(), BookingStatus.CANCELLED);
    }

    @Override
    public void expire(Booking booking) {
        booking.getShowSeats().forEach(ShowSeat::release);
        booking.setState(new ExpiredState(), BookingStatus.EXPIRED);

    }

    @Override
    public void fail(Booking booking) {
        booking.getShowSeats().forEach(ShowSeat::release);
        booking.setState(new ExpiredState(), BookingStatus.FAILED);

    }
}
