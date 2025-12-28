package com.example.bookmyshow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "BookMyShow Backend is running!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Welcome to BookMyShow Backend!";
    }
}
