package br.com.authsrv.application.domain.auth.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("An account already exists for username: " + username);
    }
}
