package com.example.bookmyshow.dto;

import com.example.bookmyshow.model.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * BookingResponseDto
 *
 * PURPOSE:
 * - Returned to client after successful booking creation
 * - Exposes ONLY required data (no entity leakage)
 * - Immutable DTO (no setters)
 *
 * WHY THIS FIELDS:
 * - bookingId → reference for future actions (payment, cancellation)
 * - status → booking lifecycle state
 */
@Getter
@AllArgsConstructor
public class BookingResponseDto {

    private final Long bookingId;
    private final BookingStatus status;
}
