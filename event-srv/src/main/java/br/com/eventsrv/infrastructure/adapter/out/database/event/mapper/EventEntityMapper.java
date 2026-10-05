package br.com.eventsrv.infrastructure.adapter.out.database.event.mapper;

import br.com.eventsrv.application.domain.event.entity.Event;
import br.com.eventsrv.infrastructure.adapter.out.database.event.entity.EventEntity;

public class EventEntityMapper {

    public EventEntity toEntity(Event event) {
        return new EventEntity(
                event.id(),
                event.name(),
                event.description(),
                event.eventType(),
                event.startAt(),
                event.endAt(),
                event.status(),
                event.venueId(),
                event.organizerId(),
                event.imageUrl(),
                event.createdAt(),
                event.updatedAt()
        );
    }

    public Event toDomain(EventEntity entity) {
        return new Event(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getEventType(),
                entity.getStartAt(),
                entity.getEndAt(),
                entity.getStatus(),
                entity.getVenueId(),
                entity.getOrganizerId(),
                entity.getImageUrl(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
