package com.example.bookmyshow.model;

public enum BookingStatus {
    CREATED,    // booking initiated
    CONFIRMED,  // payment successful
    CANCELLED,  // user cancelled
    FAILED ,     // payment failed
    EXPIRED
}
