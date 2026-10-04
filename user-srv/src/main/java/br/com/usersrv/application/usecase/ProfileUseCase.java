package br.com.usersrv.application.usecase;

import br.com.usersrv.application.domain.profile.ProfileAlreadyExistsException;
import br.com.usersrv.application.domain.profile.ProfileNotFoundException;
import br.com.usersrv.application.domain.profile.UserProfile;
import br.com.usersrv.application.port.in.IProfileUseCase;
import br.com.usersrv.application.port.out.IProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileUseCase implements IProfileUseCase {

    private final IProfileRepository repository;

    public ProfileUseCase(IProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserProfile register(UserProfile profile) {
        if (repository.existsByDocument(profile.document())) {
            throw new ProfileAlreadyExistsException(profile.document());
        }
        return repository.save(profile);
    }

    @Override
    public UserProfile getProfileByAccountId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));
    }

    @Override
    public List<UserProfile> getUserProfiles(String document, String name) {
        return repository.search(document, name);
    }
}
