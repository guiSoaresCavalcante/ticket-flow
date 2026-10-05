package br.com.eventsrv.infrastructure.adapter.in.event;

import br.com.eventsrv.application.domain.event.exceptions.InvalidEventException;
import br.com.eventsrv.application.domain.venue.exceptions.VenueNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = EventController.class)
public class EventExceptionHandler {

    @ExceptionHandler(InvalidEventException.class)
    public ResponseEntity<String> handleInvalidEvent(InvalidEventException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(VenueNotFoundException.class)
    public ResponseEntity<String> handleVenueNotFound(VenueNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
