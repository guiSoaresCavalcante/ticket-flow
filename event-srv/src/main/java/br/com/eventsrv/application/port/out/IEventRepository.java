package br.com.eventsrv.application.port.out;

import br.com.eventsrv.application.domain.event.entity.Event;

import java.util.List;

public interface IEventRepository {

    Event save(Event event);
    List<Event> findAll();
}
