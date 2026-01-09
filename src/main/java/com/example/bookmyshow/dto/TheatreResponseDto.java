package com.example.bookmyshow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheatreResponseDto {
    private Long id;
    private String name;
    private Long cityId;
    private String cityName;
}
