package com.example.bookmyshow.service;

public interface RefundStrategy {
    double calculateRefund(double amount, long minutesBeforeShow);
}
