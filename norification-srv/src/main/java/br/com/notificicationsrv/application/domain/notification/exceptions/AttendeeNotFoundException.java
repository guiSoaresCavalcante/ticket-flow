package br.com.notificicationsrv.application.domain.notification.exceptions;

import java.util.UUID;

public class AttendeeNotFoundException extends RuntimeException {

    public AttendeeNotFoundException(UUID profileId) {
        super("Attendee not found: " + profileId);
    }
}
