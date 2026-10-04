package br.com.usersrv.application.usecase;

import br.com.usersrv.application.domain.profile.UserProfile;
import br.com.usersrv.application.port.in.IProfileUseCase;

import java.util.List;

public class ProfileUseCase implements IProfileUseCase {

    @Override
    public UserProfile register(UserProfile profile) {
        return null;
    }

    @Override
    public UserProfile getProfileByAccountId(String accountId) {
        return null;
    }

    @Override
    public List<UserProfile> getUserProfiles(String document, String name) {
        return List.of();
    }
}
