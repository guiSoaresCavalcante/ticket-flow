package br.com.eventsrv.infrastructure.adapter.out.database.event.repository;

import br.com.eventsrv.application.domain.event.entity.Event;
import br.com.eventsrv.application.port.out.IEventRepository;
import br.com.eventsrv.infrastructure.adapter.out.database.event.mapper.EventEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class EventDatabaseAdapter implements IEventRepository {

    private final EventJpaRepository jpaRepository;
    private final EventEntityMapper mapper;

    public EventDatabaseAdapter(EventJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
        this.mapper = new EventEntityMapper();
    }

    @Override
    public Event save(Event event) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(event)));
    }

    @Override
    public List<Event> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Event> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}
