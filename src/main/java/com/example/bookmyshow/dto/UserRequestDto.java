package com.example.bookmyshow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data // Lombok: generates getters, setters, toString, equals, hashCode
public class UserRequestDto {
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "email is required")
    @Email(message = "Invalid Email")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    @NotNull
    // added cityId mandatory
    private Long cityId;
}
