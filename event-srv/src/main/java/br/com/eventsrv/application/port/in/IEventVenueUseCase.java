package br.com.eventsrv.application.port.in;

import br.com.eventsrv.application.domain.venue.entity.EventVenue;

import java.util.List;

public interface IEventVenueUseCase {

    EventVenue create(EventVenue venue);
    List<EventVenue> findAll();
}
