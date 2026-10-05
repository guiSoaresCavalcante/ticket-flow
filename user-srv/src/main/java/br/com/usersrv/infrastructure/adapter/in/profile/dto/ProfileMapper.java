package br.com.usersrv.infrastructure.adapter.in.profile.dto;

import br.com.usersrv.application.domain.profile.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public UserProfile toDomain(UserRegistrationRequest request) {
        return new UserProfile(null, request.name(), request.document(), request.profileType(),
                request.email(), request.phoneNumber());
    }

    public UserProfileResponse toResponse(UserProfile profile) {
        return new UserProfileResponse(
                profile.profileId(),
                profile.name(),
                profile.document(),
                profile.profileType(),
                profile.email(),
                profile.phoneNumber()
        );
    }
}
