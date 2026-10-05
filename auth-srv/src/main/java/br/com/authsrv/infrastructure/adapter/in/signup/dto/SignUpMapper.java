package br.com.authsrv.infrastructure.adapter.in.signup.dto;

import br.com.authsrv.application.port.in.dto.SignUpInput;
import br.com.authsrv.application.port.in.dto.SignUpOutput;
import org.springframework.stereotype.Component;

@Component
public class SignUpMapper {

    public SignUpInput toInput(SignUpRequest request) {
        return new SignUpInput(
            request.username(),
            request.password(),
            request.name(),
            request.document(),
            request.profileType(),
            request.email(),
            request.phoneNumber()
        );
    }

    public SignUpResponse toResponse(SignUpOutput output) {
        return new SignUpResponse(output.accountId(), output.username(), output.profileId());
    }
}
