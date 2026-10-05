package br.com.eventsrv.infrastructure.adapter.out.database.venue.repository;

import br.com.eventsrv.application.domain.venue.entity.EventVenue;
import br.com.eventsrv.application.port.out.IEventVenueRepository;
import br.com.eventsrv.infrastructure.adapter.out.database.venue.mapper.EventVenueEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EventVenueDatabaseAdapter implements IEventVenueRepository {

    private final EventVenueJpaRepository jpaRepository;
    private final EventVenueEntityMapper mapper;

    public EventVenueDatabaseAdapter(EventVenueJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
        this.mapper = new EventVenueEntityMapper();
    }

    @Override
    public EventVenue save(EventVenue venue) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(venue)));
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public List<EventVenue> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
}
