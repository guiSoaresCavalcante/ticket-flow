package br.com.usersrv.infrastructure.adapter.out.database.profile.repository;

import br.com.usersrv.infrastructure.adapter.out.database.profile.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserProfileJpaRepository extends JpaRepository<UserProfileEntity, UUID> {

    boolean existsByDocument(String document);
    List<UserProfileEntity> findByDocumentContainingIgnoreCaseAndNameContainingIgnoreCase(String document, String name);
    List<UserProfileEntity> findByDocumentContainingIgnoreCase(String document);
    List<UserProfileEntity> findByNameContainingIgnoreCase(String name);
}
