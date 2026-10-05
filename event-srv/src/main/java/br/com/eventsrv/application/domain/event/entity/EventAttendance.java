package br.com.eventsrv.application.domain.event.entity;

import java.util.UUID;

public record EventAttendance(
        UUID eventId,
        UUID profileId
) {
}
