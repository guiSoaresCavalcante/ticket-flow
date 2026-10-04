package br.com.usersrv.infrastructure.adapter.out.database.profile.repository;

import br.com.usersrv.application.domain.profile.UserProfile;
import br.com.usersrv.application.port.out.IProfileRepository;
import br.com.usersrv.infrastructure.adapter.out.database.profile.entity.UserProfileEntity;
import br.com.usersrv.infrastructure.adapter.out.database.profile.mapper.UserProfileEntityMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProfileDatabaseAdapter implements IProfileRepository {

    private final UserProfileJpaRepository jpaRepository;
    private final UserProfileEntityMapper mapper;

    public ProfileDatabaseAdapter(UserProfileJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
        this.mapper = new UserProfileEntityMapper();
    }

    @Override
    public UserProfile save(UserProfile profile) {
        UserProfileEntity saved = jpaRepository.save(mapper.toEntity(profile));
        return mapper.toDomain(saved);
    }

    @Override
    public boolean existsByDocument(String document) {
        return jpaRepository.existsByDocument(document);
    }

    @Override
    public Optional<UserProfile> findById(String id) {
        return jpaRepository.findById(UUID.fromString(id)).map(mapper::toDomain);
    }

    @Override
    public List<UserProfile> search(String document, String name) {
        boolean hasDocument = StringUtils.hasText(document);
        boolean hasName = StringUtils.hasText(name);

        List<UserProfileEntity> entities;
        if (hasDocument && hasName) {
            entities = jpaRepository.findByDocumentContainingIgnoreCaseAndNameContainingIgnoreCase(document, name);
        } else if (hasDocument) {
            entities = jpaRepository.findByDocumentContainingIgnoreCase(document);
        } else if (hasName) {
            entities = jpaRepository.findByNameContainingIgnoreCase(name);
        } else {
            entities = jpaRepository.findAll();
        }

        return entities.stream().map(mapper::toDomain).toList();
    }
}
