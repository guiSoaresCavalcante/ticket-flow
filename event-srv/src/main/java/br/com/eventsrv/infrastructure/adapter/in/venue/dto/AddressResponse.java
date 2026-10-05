package br.com.eventsrv.infrastructure.adapter.in.venue.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AddressResponse(
        UUID id,
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String country,
        String postalCode,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
