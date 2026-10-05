package br.com.eventsrv.infrastructure.adapter.in.venue;

import br.com.eventsrv.application.domain.venue.exceptions.InvalidVenueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = EventVenueController.class)
public class EventVenueExceptionHandler {

    @ExceptionHandler(InvalidVenueException.class)
    public ResponseEntity<String> handleInvalidVenue(InvalidVenueException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
