package com.example.bookmyshow.service;

import org.springframework.stereotype.Component;

@Component
public class RefundResolver {
    public RefundStrategy resolve(long minutesBeforeShow){
        if(minutesBeforeShow>=144) return new FullRefundStrategy();
        if(minutesBeforeShow >= 60) return new PartialRefundStrategy();
        return new NoRefundStrategy();
    }
}
