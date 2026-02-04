package com.example.bookmyshow.service;

import com.example.bookmyshow.model.Show;
import org.springframework.stereotype.Component;

@Component
public class PricingStrategyFactory {
    private final SeatTypePricingStrategy seatTypePricingStrategy;
    private final WeekendPricingStrategy weekendPricingStrategy;

    public PricingStrategyFactory(SeatTypePricingStrategy seatTypePricingStrategy, WeekendPricingStrategy weekendPricingStrategy){
        this.seatTypePricingStrategy = seatTypePricingStrategy;
        this.weekendPricingStrategy = weekendPricingStrategy;
    }

    public PricingStrategy getStrategy(Show show){
        if(show.isWeekend()){
            return weekendPricingStrategy;
        }
        return seatTypePricingStrategy;

    }
}
