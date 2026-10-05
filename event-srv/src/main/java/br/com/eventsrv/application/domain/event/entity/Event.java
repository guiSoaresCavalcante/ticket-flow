package br.com.eventsrv.application.domain.event.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record Event(
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
