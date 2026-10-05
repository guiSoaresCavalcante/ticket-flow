package br.com.eventsrv.application.domain.event.exceptions;

import java.util.UUID;

public class EventAttendancePublishException extends RuntimeException {

    public EventAttendancePublishException(UUID eventId, Throwable cause) {
        super("Could not publish attendance for event: " + eventId, cause);
    }
}
