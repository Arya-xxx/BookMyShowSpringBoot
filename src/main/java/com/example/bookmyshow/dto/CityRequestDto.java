package com.example.bookmyshow.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CityRequestDto {
    @NotBlank(message = "city name cant be null")
    private String name;
}
