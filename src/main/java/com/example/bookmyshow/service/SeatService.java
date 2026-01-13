package com.example.bookmyshow.service;

import com.example.bookmyshow.dto.SeatRequestDto;
import com.example.bookmyshow.dto.SeatResponseDto;

import java.util.List;

public interface SeatService {
    SeatResponseDto createSeat(SeatRequestDto seatRequestDto);
    List<SeatResponseDto> getSeatsByScreen(Long screenId);
    void deleteSeat(Long seatId);
}
