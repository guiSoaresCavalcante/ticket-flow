package br.com.eventsrv.infrastructure.adapter.out.database.venue.repository;

import br.com.eventsrv.infrastructure.adapter.out.database.venue.entity.EventVenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventVenueJpaRepository extends JpaRepository<EventVenueEntity, UUID> {
}
