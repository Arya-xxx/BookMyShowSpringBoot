package com.example.bookmyshow.service;

import com.example.bookmyshow.dto.CityRequestDto;
import com.example.bookmyshow.dto.CityResponseDto;
import com.example.bookmyshow.exception.CityNotFoundException;
import com.example.bookmyshow.exception.CityNotFoundException;
import com.example.bookmyshow.model.City;
import com.example.bookmyshow.repository.CityRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public CityResponseDto getCityById(Long id) {
       City city = cityRepository.findById(id).orElseThrow(()-> new CityNotFoundException("City not found"));
        return new CityResponseDto(city.getId(), city.getName());
    }

    @Override
    public List<CityResponseDto> getAllCities() {
        return cityRepository.findAll().stream().map(city-> new CityResponseDto(city.getId(), city.getName())).collect(Collectors.toList());
    }

    @Override
    public CityResponseDto updateCityById(Long id, CityRequestDto cityDto) {
        City city = cityRepository.findById(id).orElseThrow(()->new CityNotFoundException("City you are trying to update not found"));
        return new CityResponseDto(city.getId(), city.getName());
    }

    @Override
    public void deleteCityById(Long id) {
        cityRepository.deleteById(id);
    }

    public CityResponseDto createCity(@Valid @RequestBody CityRequestDto cityDto){
        if(cityRepository.existsByName(cityDto.getName())){
            throw new RuntimeException("City Already Exists");
        }

        City city = new City();
        city.setName(cityDto.getName());

        City saved = cityRepository.save(city);
        return new CityResponseDto(saved.getId(), saved.getName());







    }
}
