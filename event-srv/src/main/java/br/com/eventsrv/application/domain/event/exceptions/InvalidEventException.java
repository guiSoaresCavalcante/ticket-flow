package br.com.eventsrv.application.domain.event.exceptions;

public class InvalidEventException extends RuntimeException {

    public InvalidEventException(String message) {
        super(message);
    }
}
