package br.com.eventsrv.application.usecase;

import br.com.eventsrv.application.domain.event.entity.Event;
import br.com.eventsrv.application.domain.event.entity.EventAttendance;
import br.com.eventsrv.application.domain.event.exceptions.EventNotFoundException;
import br.com.eventsrv.application.domain.event.exceptions.InvalidEventException;
import br.com.eventsrv.application.domain.venue.exceptions.VenueNotFoundException;
import br.com.eventsrv.application.port.in.IEventUseCase;
import br.com.eventsrv.application.port.out.IEventAttendancePublisher;
import br.com.eventsrv.application.port.out.IEventRepository;
import br.com.eventsrv.application.port.out.IEventVenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EventUseCase implements IEventUseCase {

    private final IEventRepository repository;
    private final IEventVenueRepository venueRepository;
    private final IEventAttendancePublisher attendancePublisher;

    public EventUseCase(IEventRepository repository, IEventVenueRepository venueRepository,
                        IEventAttendancePublisher attendancePublisher) {
        this.repository = repository;
        this.venueRepository = venueRepository;
        this.attendancePublisher = attendancePublisher;
    }

    @Override
    public Event create(Event event) {
        validate(event);
        if (event.venueId() != null && !venueRepository.existsById(event.venueId())) {
            throw new VenueNotFoundException(event.venueId());
        }
        LocalDateTime now = LocalDateTime.now();
        return repository.save(new Event(
                null,
                event.name(),
                event.description(),
                event.eventType(),
                event.startAt(),
                event.endAt(),
                event.status(),
                event.venueId(),
                event.organizerId(),
                now,
                now
        ));
    }

    @Override
    public List<Event> findAll() {
        return repository.findAll();
    }

    @Override
    public Event findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
    }

    @Override
    public void attend(UUID eventId, UUID profileId) {
        if (!repository.existsById(eventId)) {
            throw new EventNotFoundException(eventId);
        }
        attendancePublisher.publish(new EventAttendance(eventId, profileId));
    }

    private void validate(Event event) {
        if (!StringUtils.hasText(event.name())) {
            throw new InvalidEventException("name is required");
        }
        if (!StringUtils.hasText(event.eventType())) {
            throw new InvalidEventException("eventType is required");
        }
        if (event.startAt() == null) {
            throw new InvalidEventException("startAt is required");
        }
        if (event.endAt() != null && event.endAt().isBefore(event.startAt())) {
            throw new InvalidEventException("endAt must not be before startAt");
        }
        if (!StringUtils.hasText(event.status())) {
            throw new InvalidEventException("status is required");
        }
        if (event.organizerId() == null) {
            throw new InvalidEventException("organizerId is required");
        }
    }
}
