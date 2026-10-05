package br.com.notificicationsrv.infrastructure.adapter.out.client.event.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventSrvEventResponse(
        UUID id,
        String name,
        String description,
        LocalDateTime startAt,
        LocalDateTime endAt
) {
}
