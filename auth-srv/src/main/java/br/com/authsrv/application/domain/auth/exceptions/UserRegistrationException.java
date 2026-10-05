package br.com.authsrv.application.domain.auth.exceptions;

public class UserRegistrationException extends RuntimeException {

    private final int statusCode;

    public UserRegistrationException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
