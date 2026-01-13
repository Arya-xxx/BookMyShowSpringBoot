package com.example.bookmyshow.repository;

import com.example.bookmyshow.model.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("Select ss from showSeat ss where ss.id in (:ids) and status= 'AVAILABLE'")
    List<ShowSeat> findAvailableSeatsForUpdate(List<Long> ids);

}
