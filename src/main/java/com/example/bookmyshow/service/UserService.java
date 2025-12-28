package com.example.bookmyshow.service;

import com.example.bookmyshow.dto.UserRequestDto;
import com.example.bookmyshow.dto.UserResponseDto;
import com.example.bookmyshow.model.User;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userDto);

    public List<UserResponseDto> getAllUsers();

    UserResponseDto updateUser(Long id, UserRequestDto userDto);

    UserResponseDto getUserById(Long id);

    void deleteUser(Long id);
}
