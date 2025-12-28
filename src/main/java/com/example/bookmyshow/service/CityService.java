package com.example.bookmyshow.service;

import com.example.bookmyshow.dto.CityRequestDto;
import com.example.bookmyshow.dto.CityResponseDto;

import java.util.List;

public interface CityService {
    public CityResponseDto createCity(CityRequestDto cityDto);
    public CityResponseDto getCityById(Long id);
    public List<CityResponseDto>getAllCities();

    CityResponseDto updateCityById(Long id, CityRequestDto cityDto);

    public void deleteCityById(Long id);
}
