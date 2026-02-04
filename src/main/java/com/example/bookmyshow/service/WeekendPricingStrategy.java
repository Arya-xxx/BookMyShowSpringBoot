package com.example.bookmyshow.service;

import com.example.bookmyshow.model.ShowSeat;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class WeekendPricingStrategy implements PricingStrategy{

    private final PricingStrategy baseStrategy;

    public WeekendPricingStrategy(PricingStrategy baseStrategy){
        this.baseStrategy = baseStrategy;
    }
    @Override
    public double calculatePrice(List<ShowSeat> seats) {
        double base = baseStrategy.calculatePrice(seats);
        return base*1.2;
    }
}
