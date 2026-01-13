package com.example.bookmyshow.dto;

import com.example.bookmyshow.model.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatResponseDto {
    private Long seatId;
    private SeatType seatType;
    private Long seatNumber;
    private Long screenId;
    private String screenName;
}
