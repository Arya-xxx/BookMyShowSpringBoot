package com.example.bookmyshow.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("NoRefund")
public class NoRefundStrategy implements RefundStrategy{
    @Override
    public double calculateRefund(double amount, long minutesBeforeShow) {
        return 0;
    }
}
