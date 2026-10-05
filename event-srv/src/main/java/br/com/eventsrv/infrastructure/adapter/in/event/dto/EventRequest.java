package br.com.eventsrv.infrastructure.adapter.in.event.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventRequest(
        String name,
        String description,
        String eventType,
        LocalDateTime startAt,
        LocalDateTime endAt,
        String status,
        UUID venueId,
        String imageUrl
) {
}
