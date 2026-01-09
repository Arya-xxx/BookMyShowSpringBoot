package com.example.bookmyshow.service;

import com.example.bookmyshow.dto.ScreenRequestDto;
import com.example.bookmyshow.dto.ScreenResponseDto;
import com.example.bookmyshow.exception.TheatreNotFoundException;
import com.example.bookmyshow.model.Screen;
import com.example.bookmyshow.model.Theatre;
import com.example.bookmyshow.repository.ScreenRepository;
import com.example.bookmyshow.repository.TheatreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreenServicImpl implements ScreenService{

    private final ScreenRepository screenRepository;
    private final TheatreRepository theatreRepository;

    @Override
    public ScreenResponseDto createScreen(ScreenRequestDto screenRequestDto) {

        Theatre theatre = theatreRepository.findById(screenRequestDto.getTheatreId()).orElseThrow(()-> new TheatreNotFoundException("Theatre Not Found"));
        Screen screen = new Screen(screenRequestDto.getName(), theatre);

        Screen saved = screenRepository.save(screen);

        return new ScreenResponseDto(saved.getId(), saved.getName(), theatre.getId(), theatre.getName());


    }

    @Override
    public List<ScreenResponseDto> getScreensByTheatre(Long theatreId) {
        return  screenRepository.findByTheatreId(theatreId).stream().map((screen)->new ScreenResponseDto(screen.getId(), screen.getName(), screen.getTheatre().getId(), screen.getTheatre().getName())).collect(Collectors.toList());
    }

    @Override
    public ScreenResponseDto getScreenById(Long id) {
        Screen screen = screenRepository.findById(id).orElseThrow(()-> new ScreenNotFoundException("Theatre Not Found"));;
        new ScreenResponseDto(screen.getId(), screen.getName(), screen.getTheatre().getId(), screen.getTheatre().getName());
    }

    @Override
    public ScreenResponseDto updateScreen(Long id, ScreenRequestDto screenRequestDto) {

        Screen screen = screenRepository.findById(id).orElseThrow(()->new ScreenNotFoundException("Screen Not Found"));

        if(!screenRequestDto.getTheatreId().equals(screen.getTheatre().getId())){
            Theatre theatre = theatreRepository.findById(screenRequestDto.getTheatreId()).orElseThrow(()->new TheatreNotFoundException("Theatre Not Found"));
            screen.updateTheatre(theatre);
        }

        Screen saved = screenRepository.save(screen);

        return new ScreenResponseDto(saved.getId(), saved.getName(), saved.getTheatre().getId(), saved.getTheatre().getName());

    }

    @Override
    public void deleteScreen(Long id) {
        screenRepository.deleteById(id);
    }
}
