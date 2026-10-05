package br.com.eventsrv.infrastructure.adapter.in.event.dto;

import br.com.eventsrv.application.domain.event.entity.Event;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EventMapper {

    public Event toDomain(EventRequest request, UUID organizerId) {
        return new Event(
                null,
                request.name(),
                request.description(),
                request.eventType(),
                request.startAt(),
                request.endAt(),
                request.status(),
                request.venueId(),
                organizerId,
                null,
                null
        );
    }

    public EventResponse toResponse(Event event) {
        return new EventResponse(
                event.id(),
                event.name(),
                event.description(),
                event.eventType(),
                event.startAt(),
                event.endAt(),
                event.status(),
                event.venueId(),
                event.organizerId(),
                event.createdAt(),
                event.updatedAt()
        );
    }
}
