package br.com.usersrv.application.domain.profile;

public class ProfileAlreadyExistsException extends RuntimeException {

    public ProfileAlreadyExistsException(String document) {
        super("A profile already exists for document: " + document);
    }
}
