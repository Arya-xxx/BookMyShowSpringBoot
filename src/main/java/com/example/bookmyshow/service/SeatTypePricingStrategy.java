package com.example.bookmyshow.service;

import com.example.bookmyshow.model.ShowSeat;

import java.util.List;

public class SeatTypePricingStrategy implements PricingStrategy{

    @Override
    public double calculatePrice(List<ShowSeat> seats) {
        return seats.stream().mapToDouble(seat-> seat.getPrice()).sum();
    }
}
