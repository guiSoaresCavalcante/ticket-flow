package br.com.usersrv.infrastructure.adapter.out.database.profile.mapper;

import br.com.usersrv.application.domain.profile.UserProfile;
import br.com.usersrv.infrastructure.adapter.out.database.profile.entity.UserProfileEntity;

import java.util.UUID;

public class UserProfileEntityMapper {

    public UserProfileEntity toEntity(UserProfile profile) {
        UUID id = profile.profileId() != null ? UUID.fromString(profile.profileId()) : null;
        return new UserProfileEntity(id, profile.name(), profile.document(), profile.profileType());
    }

    public UserProfile toDomain(UserProfileEntity entity) {
        return new UserProfile(
                entity.getId().toString(),
                entity.getName(),
                entity.getDocument(),
                entity.getProfileType()
        );
    }
}
