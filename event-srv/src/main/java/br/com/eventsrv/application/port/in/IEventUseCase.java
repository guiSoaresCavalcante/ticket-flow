package br.com.eventsrv.application.port.in;

import br.com.eventsrv.application.domain.event.entity.Event;

import java.util.List;
import java.util.UUID;

public interface IEventUseCase {

    Event create(Event event);
    List<Event> findAll();
    Event findById(UUID id);
}
