package br.com.notificicationsrv.application.domain.notification.entity;

import java.util.UUID;

public record Attendee(
        UUID profileId,
        String name,
        String email,
        String phoneNumber
) {
}
