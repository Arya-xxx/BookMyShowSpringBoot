package com.example.bookmyshow.service;

import com.example.bookmyshow.dto.BookingResponseDto;

import java.util.List;

public interface BookingService {

    public BookingResponseDto createBooking(Long userId, Long showId, List<Long> showSeatIds);
}
