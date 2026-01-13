package com.example.bookmyshow.repository;

import com.example.bookmyshow.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    public List<Seat> findByScreenId(Long id);
}
