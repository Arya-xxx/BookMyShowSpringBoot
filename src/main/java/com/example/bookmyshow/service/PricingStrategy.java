package com.example.bookmyshow.service;

import com.example.bookmyshow.model.ShowSeat;

import java.util.List;

public interface PricingStrategy {
    double calculatePrice(List<ShowSeat>seats);
}
