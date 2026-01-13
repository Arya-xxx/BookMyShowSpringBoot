package com.example.bookmyshow.service;

import com.example.bookmyshow.dto.SeatRequestDto;
import com.example.bookmyshow.dto.SeatResponseDto;
import com.example.bookmyshow.exception.ScreenNotFoundException;
import com.example.bookmyshow.exception.SeatNotFoundException;
import com.example.bookmyshow.model.Screen;
import com.example.bookmyshow.model.Seat;
import com.example.bookmyshow.repository.ScreenRepository;
import com.example.bookmyshow.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Lombok: injects final dependencies via constructor
public class SeatServiceImpl implements SeatService {

    // Repository for Seat entity CRUD operations
    private final SeatRepository seatRepository;

    // Repository to validate & fetch Screen before creating Seat
    private final ScreenRepository screenRepository;

    /**
     * Creates a new Seat for a given Screen
     *
     * Flow:
     * 1️⃣ Validate Screen existence
     * 2️⃣ Create Seat entity using controlled constructor
     * 3️⃣ Persist Seat
     * 4️⃣ Return SeatResponseDto (no entity exposure)
     */
    @Override
    public SeatResponseDto createSeat(SeatRequestDto seatRequestDto) {

        // 1️⃣ Fetch Screen (ensures seat is always linked to a valid screen)
        Screen screen = screenRepository.findById(seatRequestDto.getScreenId())
                .orElseThrow(() -> new ScreenNotFoundException("Screen Not Found"));

        // 2️⃣ Create Seat entity (NO setters → controlled creation)
        Seat seat = new Seat(
                seatRequestDto.getSeatNumber(),
                seatRequestDto.getSeatType(),
                screen
        );

        // 3️⃣ Persist Seat
        Seat saved = seatRepository.save(seat);

        // 4️⃣ Map Entity → Response DTO
        return new SeatResponseDto(
                saved.getId(),
                saved.getSeatType(),
                saved.getSeatNumber(),
                screen.getId(),
                screen.getName()
        );
    }

    /**
     * Fetches all seats for a given screen
     *
     * Important:
     * - Uses derived query: findByScreenId()
     * - Single DB call → avoids N+1 problem
     * - Returns DTOs, not entities
     */
    @Override
    public List<SeatResponseDto> getSeatsByScreen(Long screenId) {

        // 1️⃣ Validate Screen existence (business rule)
        screenRepository.findById(screenId)
                .orElseThrow(() -> new ScreenNotFoundException("Screen Not Found"));

        // 2️⃣ Fetch seats using screenId (efficient DB query)
        return seatRepository.findByScreenId(screenId)
                .stream()
                .map(seat -> new SeatResponseDto(
                        seat.getId(),
                        seat.getSeatType(),
                        seat.getSeatNumber(),
                        seat.getScreen().getId(),
                        seat.getScreen().getName()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Deletes a seat by ID
     *
     * Flow:
     * 1️⃣ Validate seat existence
     * 2️⃣ Delete seat
     *
     * Why not deleteById directly?
     * - Allows us to throw custom exception
     * - Enables future business checks (e.g., booked seat)
     */
    @Override
    public void deleteSeat(Long seatId) {

        // 1️⃣ Ensure seat exists
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new SeatNotFoundException("Seat Not Found"));

        // 2️⃣ Delete seat
        seatRepository.delete(seat);
    }
}
