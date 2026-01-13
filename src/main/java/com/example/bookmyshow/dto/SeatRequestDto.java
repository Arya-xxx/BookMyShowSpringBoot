package com.example.bookmyshow.dto;

import com.example.bookmyshow.model.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatRequestDto {
    private SeatType seatType;
    private Long screenId;
    private Long seatNumber;
}
