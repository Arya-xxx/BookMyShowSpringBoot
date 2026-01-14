package com.example.bookmyshow.model;

public interface BookingState {

     void lockSeats(Booking booking);

     void confirm(Booking booking);

     void cancel(Booking booking);

     void expire(Booking booking);

     void fail(Booking booking);


}
