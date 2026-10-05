package br.com.eventsrv.application.port.out;

import br.com.eventsrv.application.domain.event.entity.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEventRepository {

    Event save(Event event);
    List<Event> findAll();
    Optional<Event> findById(UUID id);
    boolean existsById(UUID id);
}
