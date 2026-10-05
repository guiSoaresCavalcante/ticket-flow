package br.com.eventsrv.infrastructure.adapter.in.event.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventResponse(
        UUID id,
        String name,
        String description,
        String eventType,
        LocalDateTime startAt,
        LocalDateTime endAt,
        String status,
        UUID venueId,
        UUID organizerId,
        String imageUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
