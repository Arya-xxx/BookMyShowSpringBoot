package com.example.bookmyshow.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fullRefund")
public class FullRefundStrategy implements RefundStrategy{
    @Override
    public double calculateRefund(double amount, long minutesBeforeShow) {
        return amount*0.9;
    }
}
