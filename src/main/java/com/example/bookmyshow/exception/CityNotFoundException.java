package com.example.bookmyshow.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String message){
        super(message);
    }
}
