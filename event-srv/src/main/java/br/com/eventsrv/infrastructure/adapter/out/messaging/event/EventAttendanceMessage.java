package br.com.eventsrv.infrastructure.adapter.out.messaging.event;

import java.util.UUID;

public record EventAttendanceMessage(
        UUID eventId,
        UUID profileId
) {
}
