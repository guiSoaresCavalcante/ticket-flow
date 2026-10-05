package br.com.notificicationsrv.application.domain.notification.exceptions;

import java.util.UUID;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(UUID eventId) {
        super("Event not found: " + eventId);
    }
}
