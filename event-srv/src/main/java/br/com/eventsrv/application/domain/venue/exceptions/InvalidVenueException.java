package br.com.eventsrv.application.domain.venue.exceptions;

public class InvalidVenueException extends RuntimeException {

    public InvalidVenueException(String message) {
        super(message);
    }
}
