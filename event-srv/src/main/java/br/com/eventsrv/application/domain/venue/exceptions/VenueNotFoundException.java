package br.com.eventsrv.application.domain.venue.exceptions;

import java.util.UUID;

public class VenueNotFoundException extends RuntimeException {

    public VenueNotFoundException(UUID id) {
        super("Venue not found: " + id);
    }
}
