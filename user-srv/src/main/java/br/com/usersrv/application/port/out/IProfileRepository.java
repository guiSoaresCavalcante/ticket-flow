package br.com.usersrv.application.port.out;

import br.com.usersrv.application.domain.profile.UserProfile;

import java.util.List;
import java.util.Optional;

public interface IProfileRepository {

    UserProfile save(UserProfile profile);
    boolean existsByDocument(String document);
    Optional<UserProfile> findById(String id);
    List<UserProfile> search(String document, String name);
}
