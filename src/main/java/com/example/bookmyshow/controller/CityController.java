package com.example.bookmyshow.controller;

import com.example.bookmyshow.dto.CityRequestDto;
import com.example.bookmyshow.dto.CityResponseDto;
import com.example.bookmyshow.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    /*
     * Create city
     */
    @PostMapping
    public CityResponseDto create(@Valid @RequestBody CityRequestDto dto) {
        return cityService.createCity(dto);
    }

    /*
     * Get all cities
     */
    @GetMapping
    public List<CityResponseDto> getAll() {
        return cityService.getAllCities();
    }

    /*
     * Get city by id
     */
    @GetMapping("/{id}")
    public CityResponseDto getById(@PathVariable Long id) {
        return cityService.getCityById(id);
    }

    /*
     * Delete city
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        cityService.deleteCityById(id);
        return "City deleted successfully";
    }
}
