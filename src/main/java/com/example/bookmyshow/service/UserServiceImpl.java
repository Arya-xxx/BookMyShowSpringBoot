package com.example.bookmyshow.service;

import com.example.bookmyshow.dto.UserRequestDto;
import com.example.bookmyshow.dto.UserResponseDto;
import com.example.bookmyshow.exception.UserAlreadyExistsException;
import com.example.bookmyshow.exception.UserNotFoundException;
import com.example.bookmyshow.model.User;
import com.example.bookmyshow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    // must add final for @RequiredArgsConstructor to work

//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserResponseDto createUser(UserRequestDto userDto) {

        // 1️⃣ DTO → Entity mapping
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());

        // 2️⃣ Business validation: check uniqueness
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(" Hi Email already exists !");
        }

        // 3️⃣ Persist entity
        User saved = userRepository.save(user);

        // 4️⃣ Entity → Response DTO mapping
        return new UserResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getEmail()
        );
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail()))
                .collect(Collectors.toList());

    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userDto) {
        User user = new User();

        userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));

        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());

        User updatedUser = userRepository.save(user);

        return new UserResponseDto(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getEmail()
        );

    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
    }

    public UserResponseDto getUserById(Long id){

       User user =  userRepository.findById(id).orElseThrow(()->  new UserNotFoundException("User with this id not found"));
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
