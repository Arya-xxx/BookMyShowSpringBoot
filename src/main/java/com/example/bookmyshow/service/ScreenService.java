package com.example.bookmyshow.service;

import com.example.bookmyshow.dto.ScreenRequestDto;
import com.example.bookmyshow.dto.ScreenResponseDto;

import java.util.List;

public interface ScreenService {
    ScreenResponseDto createScreen(ScreenRequestDto dto);
    List<ScreenResponseDto> getScreensByTheatre(Long theatreId);
    ScreenResponseDto getScreenById(Long id);
    ScreenResponseDto updateScreen(Long id, ScreenRequestDto dto);
    void deleteScreen(Long id);
}
