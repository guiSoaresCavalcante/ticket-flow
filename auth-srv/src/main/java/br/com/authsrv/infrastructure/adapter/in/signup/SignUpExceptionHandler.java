package br.com.authsrv.infrastructure.adapter.in.signup;

import br.com.authsrv.application.domain.auth.exceptions.UserAlreadyExistsException;
import br.com.authsrv.application.domain.auth.exceptions.UserRegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SignUpExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserRegistrationException.class)
    public ResponseEntity<String> handleUserRegistrationError(UserRegistrationException ex) {
        return ResponseEntity.status(HttpStatus.valueOf(ex.getStatusCode())).body(ex.getMessage());
    }
}
