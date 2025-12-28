package com.example.bookmyshow.dto;

import lombok.*;

@Data
@AllArgsConstructor // Lombok: generates constructor with all fields
public class UserResponseDto {
    private long id;
    private String name;
    private String email;
}
