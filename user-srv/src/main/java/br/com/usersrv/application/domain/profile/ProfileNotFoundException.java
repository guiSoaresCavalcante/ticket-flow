package br.com.usersrv.application.domain.profile;

public class ProfileNotFoundException extends RuntimeException {

    public ProfileNotFoundException(String id) {
        super("No profile found for id: " + id);
    }
}
