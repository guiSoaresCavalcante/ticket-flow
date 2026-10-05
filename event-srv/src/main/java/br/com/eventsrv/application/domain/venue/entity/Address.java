package br.com.eventsrv.application.domain.venue.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record Address(
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
