package com.example.bookmyshow.repository;

import com.example.bookmyshow.model.City;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    boolean existsByName(@NotBlank(message = "city name cant be null") String name);

    @Override
    boolean existsById(Long id);
}
