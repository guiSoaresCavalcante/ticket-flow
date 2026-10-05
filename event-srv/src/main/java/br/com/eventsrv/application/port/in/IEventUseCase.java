package br.com.eventsrv.application.port.in;

import br.com.eventsrv.application.domain.event.entity.Event;

import java.util.List;

public interface IEventUseCase {

    Event create(Event event);
    List<Event> findAll();
}
