package com.example.bookmyshow.service;

import com.example.bookmyshow.dto.TheatreRequestDto;
import com.example.bookmyshow.dto.TheatreResponseDto;

import java.util.List;

public interface TheatreService {
    public TheatreResponseDto createTheatre(TheatreRequestDto theatreRequestDto);
    public List<TheatreResponseDto> getAllTheatres();
    public TheatreResponseDto getTheatreById(Long id);
    public TheatreResponseDto updateTheatre( Long id,TheatreRequestDto theatreRequestDto);
    public void deleteTheatreById(Long id);

}
