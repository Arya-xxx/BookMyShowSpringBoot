package com.example.bookmyshow.controller;

import com.example.bookmyshow.dto.UserRequestDto;
import com.example.bookmyshow.dto.UserResponseDto;
import com.example.bookmyshow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // returns json response @RestController = @Controller + @ResponseBody
/*
@Controller
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "hello";
    }
    // looks for hello.html
}
Use @RestController for APIs that return data.
Use @Controller for UI/views.
 */
@RequestMapping("api/users")
@RequiredArgsConstructor // Injected automatically
public class UserController {

    private final UserService userService;

    @PostMapping
    UserResponseDto createUser(@Valid @RequestBody UserRequestDto userDto){ // must add @Valid, @RequestBody
      //  @RequestBody converts JSON → Java object


      return  userService.createUser(userDto);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }
    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody @Valid UserRequestDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById (@PathVariable Long id){
        return userService.getUserById(id);
    }

}
