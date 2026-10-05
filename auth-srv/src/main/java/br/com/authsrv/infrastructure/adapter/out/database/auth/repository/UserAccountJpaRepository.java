package br.com.authsrv.infrastructure.adapter.out.database.auth.repository;

import br.com.authsrv.infrastructure.adapter.out.database.auth.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserAccountJpaRepository extends JpaRepository<UserAccountEntity, UUID> {
    boolean existsByUsername(String username);
    Optional<UserAccountEntity> findByUsername(String username);
}
