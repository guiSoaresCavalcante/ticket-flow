package br.com.eventsrv.infrastructure.adapter.in.venue;

import br.com.eventsrv.application.domain.venue.entity.EventVenue;
import br.com.eventsrv.application.port.in.IEventVenueUseCase;
import br.com.eventsrv.infrastructure.adapter.in.venue.dto.EventVenueMapper;
import br.com.eventsrv.infrastructure.adapter.in.venue.dto.EventVenueRequest;
import br.com.eventsrv.infrastructure.adapter.in.venue.dto.EventVenueResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventVenueController implements SwaggerEventVenueController {

    private final IEventVenueUseCase useCase;
    private final EventVenueMapper mapper;

    public EventVenueController(IEventVenueUseCase useCase, EventVenueMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<EventVenueResponse> create(EventVenueRequest request) {
        EventVenue venue = useCase.create(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(venue));
    }

    @Override
    public ResponseEntity<List<EventVenueResponse>> findAll() {
        return ResponseEntity.ok(useCase.findAll().stream().map(mapper::toResponse).toList());
    }
}
