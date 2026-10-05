package br.com.notificicationsrv.application.domain.notification.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventDetails(
        UUID id,
        String name,
        String description,
        LocalDateTime startAt,
        LocalDateTime endAt
) {
}
