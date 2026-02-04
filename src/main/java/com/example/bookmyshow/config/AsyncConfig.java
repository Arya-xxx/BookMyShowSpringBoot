package com.example.bookmyshow.config;

import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AsyncConfig {

    public ExecutorService bookingExecutor(){
        return Executors.newFixedThreadPool(10);
    }
}
