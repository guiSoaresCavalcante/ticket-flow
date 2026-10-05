package br.com.notificicationsrv.application.port.in.dto;

import java.util.UUID;

public record SendNotificationInput(
        UUID eventId,
        UUID profileId
) {
}
