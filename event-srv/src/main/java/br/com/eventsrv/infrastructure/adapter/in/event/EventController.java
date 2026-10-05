package br.com.eventsrv.infrastructure.adapter.in.event;

import br.com.eventsrv.application.domain.auth.entity.AuthenticatedUser;
import br.com.eventsrv.application.domain.event.entity.Event;
import br.com.eventsrv.application.domain.event.exceptions.InvalidEventException;
import br.com.eventsrv.application.port.in.IEventUseCase;
import br.com.eventsrv.infrastructure.adapter.in.event.dto.EventMapper;
import br.com.eventsrv.infrastructure.adapter.in.event.dto.EventRequest;
import br.com.eventsrv.infrastructure.adapter.in.event.dto.EventResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class EventController implements SwaggerEventController {

    private final IEventUseCase useCase;
    private final EventMapper mapper;

    public EventController(IEventUseCase useCase, EventMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<EventResponse> create(AuthenticatedUser user, EventRequest request) {
        Event event = useCase.create(mapper.toDomain(request, organizerId(user)));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(event));
    }

    @Override
    public ResponseEntity<List<EventResponse>> findAll() {
        return ResponseEntity.ok(useCase.findAll().stream().map(mapper::toResponse).toList());
    }

    @Override
    public ResponseEntity<EventResponse> findById(UUID eventId) {
        return ResponseEntity.ok(mapper.toResponse(useCase.findById(eventId)));
    }

    @Override
    public ResponseEntity<Void> attend(UUID eventId, AuthenticatedUser user) {
        useCase.attend(eventId, organizerId(user));
        return ResponseEntity.accepted().build();
    }

    private UUID organizerId(AuthenticatedUser user) {
        try {
            return UUID.fromString(user.profileId());
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new InvalidEventException("token profileId is missing or invalid");
        }
    }
}
