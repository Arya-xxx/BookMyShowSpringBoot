package com.example.bookmyshow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ScreenResponseDto {
    private Long id;
    private String name;
    private Long theatreId;
    private String theatreName;
}
