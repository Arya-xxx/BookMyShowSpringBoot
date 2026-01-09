package com.example.bookmyshow.controller;

import com.example.bookmyshow.dto.TheatreRequestDto;
import com.example.bookmyshow.dto.TheatreResponseDto;
import com.example.bookmyshow.service.TheatreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/theaters")
public class TheatreController {
    private final TheatreService theatreService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TheatreResponseDto createTheatre(@Valid @RequestBody TheatreRequestDto theatreRequestDto){
        return theatreService.createTheatre(theatreRequestDto);
    }
    @GetMapping
    public List<TheatreResponseDto> getAllTheatres(){
        return theatreService.getAllTheatres();
    }
    @GetMapping("/{id}")
    public TheatreResponseDto getTheatreById(Long id){
        return theatreService.getTheatreById(id);
    }
    @PutMapping("/{id}")
    public TheatreResponseDto updateTheatre(Long id,@Valid @RequestBody TheatreRequestDto theatreRequestDto){
        return theatreService.updateTheatre(id,theatreRequestDto);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTheatreById(Long id){
         theatreService.deleteTheatreById(id);
    }
}
