package com.example.bookmyshow.controller;

import com.example.bookmyshow.dto.SeatRequestDto;
import com.example.bookmyshow.dto.SeatResponseDto;
import com.example.bookmyshow.service.SeatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller responsible for handling all Seat-related APIs.
 * This layer ONLY handles HTTP requests/responses.
 * Business logic is delegated to SeatService.
 */
@RestController
@RequestMapping("/api/seats") // ✅ Base URL for all seat APIs
@RequiredArgsConstructor // Lombok generates constructor for final fields
public class SeatController {

    // Service layer dependency (injected via constructor)
    private final SeatService seatService;

    /**
     * Create a new seat for a screen
     *
     * HTTP Method: POST
     * URL: /api/seats
     *
     * @Valid → triggers validation annotations in SeatRequestDto
     * @RequestBody → converts JSON request into Java object
     */
    @PostMapping
    public SeatResponseDto createSeat(
            @Valid @RequestBody SeatRequestDto seatRequestDto
    ) {
        return seatService.createSeat(seatRequestDto);
    }

    /**
     * Get all seats belonging to a particular screen
     *
     * HTTP Method: GET
     * URL: /api/seats?screenId=1
     *
     * @RequestParam → extracts query parameter from URL
     */
    @GetMapping
    public List<SeatResponseDto> getSeatsByScreen(
            @RequestParam Long screenId // ✅ FIX: was missing annotation
    ) {
        return seatService.getSeatsByScreen(screenId);
    }

    /**
     * Delete a seat by its ID
     *
     * HTTP Method: DELETE
     * URL: /api/seats/{id}
     *
     * @PathVariable → extracts ID from URL path
     */
    @DeleteMapping("/{id}")
    public void deleteSeat(
            @PathVariable Long id // ✅ FIX: was missing annotation & name mismatch
    ) {
        seatService.deleteSeat(id);
    }
}
