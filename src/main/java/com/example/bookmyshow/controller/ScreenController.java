package com.example.bookmyshow.controller;

import com.example.bookmyshow.dto.ScreenRequestDto;
import com.example.bookmyshow.dto.ScreenResponseDto;
import com.example.bookmyshow.service.ScreenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/screen")
@RequiredArgsConstructor
public class ScreenController {

    private final ScreenService screenService;

    @PostMapping
    public ScreenResponseDto createScreen(@Valid @RequestBody ScreenRequestDto screenRequestDto){
        return screenService.createScreen(screenRequestDto);
    }
    @GetMapping("theatre/{id}")
    List<ScreenResponseDto> getScreensByTheatre(Long theatreId){
        return screenService.getScreensByTheatre(theatreId);
    }
    @GetMapping("/{id}")
    ScreenResponseDto getScreenById(Long id){
        return screenService.getScreenById(id);
    }
    @PostMapping
    ScreenResponseDto updateScreen(Long id, ScreenRequestDto screenRequestDto){
        return screenService.updateScreen(id,screenRequestDto);
    }
    @DeleteMapping("/{id}")
    void deleteScreen(Long id){
         screenService.deleteScreen(id);
    }

}
