package br.com.eventsrv.infrastructure.adapter.in.venue.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventVenueResponse(
        UUID id,
        String name,
        String description,
        Integer capacity,
        AddressResponse address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
