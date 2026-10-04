package br.com.usersrv.infrastructure.adapter.in.profile;


import br.com.usersrv.infrastructure.adapter.in.profile.dto.ProfileMapper;
import br.com.usersrv.infrastructure.adapter.in.profile.dto.UserProfileResponse;
import br.com.usersrv.infrastructure.adapter.in.profile.dto.UserRegistrationRequest;
import br.com.usersrv.application.domain.profile.UserProfile;
import br.com.usersrv.application.port.in.IProfileUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserProfileController implements SwaggerUserProfileController {

    private final IProfileUseCase useCase;
    private final ProfileMapper mapper;

    public UserProfileController(IProfileUseCase useCase, ProfileMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<UserProfileResponse> getProfileById(String id) {
        UserProfile profile = useCase.getProfileByAccountId(id);
        return ResponseEntity.ok(mapper.toResponse(profile));
    }

    @Override
    public ResponseEntity<List<UserProfileResponse>> getUsersProfiles(String document, String name) {
        List<UserProfile> profiles = useCase.getUserProfiles(document, name);
        return ResponseEntity.ok(profiles.stream().map(mapper::toResponse).toList());
    }

    @Override
    public ResponseEntity<UserProfileResponse> register(UserRegistrationRequest request) {
        UserProfile profile = useCase.register(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(profile));
    }
}
