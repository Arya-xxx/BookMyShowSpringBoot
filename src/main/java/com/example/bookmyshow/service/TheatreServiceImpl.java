package com.example.bookmyshow.service;

import com.example.bookmyshow.dto.TheatreRequestDto;
import com.example.bookmyshow.dto.TheatreResponseDto;
import com.example.bookmyshow.exception.CityNotFoundException;
import com.example.bookmyshow.exception.TheatreNotFoundException;
import com.example.bookmyshow.model.City;
import com.example.bookmyshow.model.Theatre;
import com.example.bookmyshow.repository.CityRepository;
import com.example.bookmyshow.repository.TheatreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // Lombok: generates constructor for final fields
@Service // Marks this class as Spring Service layer
public class TheatreServiceImpl implements TheatreService {

    // Injected automatically via constructor
    private final TheatreRepository theatreRepository;
    private final CityRepository cityRepository;

    /**
     * Create a new Theatre
     */
    public TheatreResponseDto createTheatre(TheatreRequestDto theatreRequestDto){

        // 1️⃣ Fetch City (FK validation)
        City city = cityRepository.findById(theatreRequestDto.getCityId())
                .orElseThrow(() -> new CityNotFoundException("City Not Found"));

        // 2️⃣ Create Theatre using controlled constructor
        Theatre theatre = new Theatre(theatreRequestDto.getName(), city);

        // 3️⃣ Persist theatre
        Theatre saved = theatreRepository.save(theatre);

        // 4️⃣ Convert Entity → DTO
        return new TheatreResponseDto(
                saved.getId(),
                saved.getName(),
                city.getId(),
                city.getName()
        );
    }

    /**
     * Fetch all theatres
     */
    @Override
    public List<TheatreResponseDto> getAllTheatres() {

        return theatreRepository.findAll()
                .stream()
                // Entity → DTO mapping
                .map(theatre -> new TheatreResponseDto(
                        theatre.getId(),
                        theatre.getName(),
                        theatre.getCity().getId(),
                        theatre.getCity().getName()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Fetch theatre by ID
     */
    @Override
    public TheatreResponseDto getTheatreById(Long id) {

        // 1️⃣ Validate existence
        Theatre theatre = theatreRepository.findById(id)
                .orElseThrow(() -> new TheatreNotFoundException("Theatre Not Found"));

        // 2️⃣ Return DTO
        return new TheatreResponseDto(
                theatre.getId(),
                theatre.getName(),
                theatre.getCity().getId(),
                theatre.getCity().getName()
        );
    }

    /**
     * Update Theatre details
     */
    @Override
    public TheatreResponseDto updateTheatre(Long id, TheatreRequestDto theatreRequestDto) {

        // 1️⃣ Fetch existing theatre
        Theatre theatre = theatreRepository.findById(id)
                .orElseThrow(() -> new TheatreNotFoundException("Theatre Not Found"));

        // 2️⃣ Update name using business method (no setters)
        theatre.updateName(theatreRequestDto.getName());

        // 3️⃣ Update city ONLY if changed
        if (!theatre.getCity().getId().equals(theatreRequestDto.getCityId())) {

            City city = cityRepository.findById(theatreRequestDto.getCityId())
                    .orElseThrow(() -> new CityNotFoundException("City Not Found"));

            theatre.changeCity(city); // business-safe method
        }

        // 4️⃣ Save updated entity
        Theatre saved = theatreRepository.save(theatre);

        // 5️⃣ Return updated DTO
        return new TheatreResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getCity().getId(),
                saved.getCity().getName()
        );
    }

    /**
     * Delete theatre safely
     */
    @Override
    public void deleteTheatreById(Long id) {

        // 1️⃣ Ensure theatre exists
        Theatre theatre = theatreRepository.findById(id)
                .orElseThrow(() -> new TheatreNotFoundException("Theatre not found with id: " + id));

        // 2️⃣ Future business rules go here
        // Example: if theatre has shows → throw exception

        // 3️⃣ Delete theatre
        theatreRepository.delete(theatre);
    }
}

