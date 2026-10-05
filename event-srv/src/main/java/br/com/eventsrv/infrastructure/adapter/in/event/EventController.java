package br.com.eventsrv.infrastructure.adapter.in.event;

import br.com.eventsrv.application.domain.event.entity.Event;
import br.com.eventsrv.application.port.in.IEventUseCase;
import br.com.eventsrv.infrastructure.adapter.in.event.dto.EventMapper;
import br.com.eventsrv.infrastructure.adapter.in.event.dto.EventRequest;
import br.com.eventsrv.infrastructure.adapter.in.event.dto.EventResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController implements SwaggerEventController {

    private final IEventUseCase useCase;
    private final EventMapper mapper;

    public EventController(IEventUseCase useCase, EventMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<EventResponse> create(EventRequest request) {
        Event event = useCase.create(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(event));
    }

    @Override
    public ResponseEntity<List<EventResponse>> findAll() {
        return ResponseEntity.ok(useCase.findAll().stream().map(mapper::toResponse).toList());
    }
}
