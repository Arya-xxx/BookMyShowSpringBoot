package com.example.bookmyshow.model;

public enum SeatType {
    REGULAR(200),
    PREMIUM(300),
    RECLINER(500);

    private final double basePrice;
    SeatType(double basePrice){
        this.basePrice=basePrice;
    }

    public double getBasePrice() {
        return basePrice;
    }
}
