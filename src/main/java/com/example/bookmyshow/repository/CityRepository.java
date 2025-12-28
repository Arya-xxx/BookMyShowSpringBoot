package com.example.bookmyshow.repository;

import com.example.bookmyshow.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
