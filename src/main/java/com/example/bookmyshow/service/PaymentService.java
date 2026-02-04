package com.example.bookmyshow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class PaymentService {
    /**
     * ExecutorService injected by Spring from AsyncConfig.
     *Injection rules (order):
     *
     * 1. By TYPE
     * 2. If multiple → @Qualifier
     * 3. If one marked @Primary → use it
     * 4. Else → fail fast
     *
     *
     * This is a dedicated thread pool used ONLY for payment-related async work.
     * Using a custom executor avoids using ForkJoinPool.commonPool(),
     * which is dangerous for blocking operations like external payment calls.
     */
    private final ExecutorService bookingExecutor;
    @Bean
    public CompletableFuture<Boolean>processPaymentAsync(Long bookingId, double amount){

        return CompletableFuture.supplyAsync(()->{

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {}

            // Random success/failure
            return Math.random() > 0.2;

        }, bookingExecutor);
    }

}
