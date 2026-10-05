package br.com.eventsrv.application.port.out;

import br.com.eventsrv.application.domain.venue.entity.EventVenue;

import java.util.List;
import java.util.UUID;

public interface IEventVenueRepository {

    EventVenue save(EventVenue venue);
    boolean existsById(UUID id);
    List<EventVenue> findAll();
}
