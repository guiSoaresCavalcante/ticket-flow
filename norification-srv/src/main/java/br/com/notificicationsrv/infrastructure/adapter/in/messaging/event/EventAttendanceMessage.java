package br.com.notificicationsrv.infrastructure.adapter.in.messaging.event;

import java.util.UUID;

public record EventAttendanceMessage(
        UUID eventId,
        UUID profileId
) {
}
