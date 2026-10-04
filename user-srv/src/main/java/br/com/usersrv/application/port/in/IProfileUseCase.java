package br.com.usersrv.application.port.in;

import br.com.usersrv.application.domain.profile.UserProfile;

import java.util.List;

public interface IProfileUseCase {

    UserProfile register(UserProfile profile);
    UserProfile getProfileByAccountId(String id);
    List<UserProfile> getUserProfiles(String document, String name);
}
