package com.example.bookmyshow.service;

import com.example.bookmyshow.dto.ScreenRequestDto;
import com.example.bookmyshow.dto.ScreenResponseDto;
import com.example.bookmyshow.exception.ScreenNotFoundException;
import com.example.bookmyshow.exception.TheatreNotFoundException;
import com.example.bookmyshow.model.Screen;
import com.example.bookmyshow.model.Theatre;
import com.example.bookmyshow.repository.ScreenRepository;
import com.example.bookmyshow.repository.TheatreRepository;
import com.example.bookmyshow.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreenServiceImpl implements ScreenService {

    // Constructor injection (best practice)
    private final ScreenRepository screenRepository;
    private final TheatreRepository theatreRepository;

    @Override
    public ScreenResponseDto createScreen(ScreenRequestDto screenRequestDto) {

        // 1️⃣ Validate parent entity (Theatre must exist)
        Theatre theatre = theatreRepository.findById(screenRequestDto.getTheatreId())
                .orElseThrow(() -> new TheatreNotFoundException("Theatre Not Found"));

        // 2️⃣ Controlled entity creation (no setters → safer domain model)
        Screen screen = new Screen(screenRequestDto.getName(), theatre);

        // 3️⃣ Persist
        Screen saved = screenRepository.save(screen);

        // 4️⃣ Entity → DTO (never expose entity directly)
        return new ScreenResponseDto(
                saved.getId(),
                saved.getName(),
                theatre.getId(),
                theatre.getName()
        );
    }

    @Override
    public List<ScreenResponseDto> getScreensByTheatre(Long theatreId) {

        // ✅ Single DB call using derived query method
        // Avoids N+1 problem
        return screenRepository.findByTheatreId(theatreId)
                .stream()
                .map(screen -> new ScreenResponseDto(
                        screen.getId(),
                        screen.getName(),
                        screen.getTheatre().getId(),
                        screen.getTheatre().getName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ScreenResponseDto getScreenById(Long id) {

        // ❗ FIXED: correct exception message
        Screen screen = screenRepository.findById(id)
                .orElseThrow(() -> new ScreenNotFoundException("Screen Not Found"));

        return new ScreenResponseDto(
                screen.getId(),
                screen.getName(),
                screen.getTheatre().getId(),
                screen.getTheatre().getName()
        );
    }

    @Override
    public ScreenResponseDto updateScreen(Long id, ScreenRequestDto screenRequestDto) {

        // 1️⃣ Fetch existing screen
        Screen screen = screenRepository.findById(id)
                .orElseThrow(() -> new ScreenNotFoundException("Screen Not Found"));

        // ❗ FIX: update screen name (was missing earlier)
        screen.updateName(screenRequestDto.getName());

        // 2️⃣ Change theatre only if different
        if (!screenRequestDto.getTheatreId().equals(screen.getTheatre().getId())) {

            Theatre theatre = theatreRepository.findById(screenRequestDto.getTheatreId())
                    .orElseThrow(() -> new TheatreNotFoundException("Theatre Not Found"));

            screen.updateTheatre(theatre);
        }

        // 3️⃣ Persist updated entity
        Screen saved = screenRepository.save(screen);

        return new ScreenResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getTheatre().getId(),
                saved.getTheatre().getName()
        );
    }

    @Override
    public void deleteScreen(Long id) {

        // ❗ FIX: validate existence before delete
        Screen screen = screenRepository.findById(id)
                .orElseThrow(() -> new ScreenNotFoundException("Screen Not Found"));

        screenRepository.delete(screen);
    }
}
