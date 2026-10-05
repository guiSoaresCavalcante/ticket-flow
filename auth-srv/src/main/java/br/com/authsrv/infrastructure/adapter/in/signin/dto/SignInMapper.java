package br.com.authsrv.infrastructure.adapter.in.signin.dto;

import br.com.authsrv.application.port.in.dto.SignInInput;
import br.com.authsrv.application.port.in.dto.SignInOutput;
import org.springframework.stereotype.Component;

@Component
public class SignInMapper {

    public SignInInput toInput(SignInRequest request) {
        return new SignInInput(request.username(), request.password());
    }

    public SignInResponse toResponse(SignInOutput output) {
        return new SignInResponse(output.accessToken(), output.tokenType(), output.expiresIn());
    }
}
