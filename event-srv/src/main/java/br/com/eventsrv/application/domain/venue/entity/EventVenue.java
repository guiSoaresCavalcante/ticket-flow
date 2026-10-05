package br.com.eventsrv.application.domain.venue.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventVenue(
        UUID id,
        String name,
        String description,
        Integer capacity,
        Address address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
