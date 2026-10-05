package br.com.eventsrv.infrastructure.adapter.out.database.event.repository;

import br.com.eventsrv.infrastructure.adapter.out.database.event.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventJpaRepository extends JpaRepository<EventEntity, UUID> {
}
