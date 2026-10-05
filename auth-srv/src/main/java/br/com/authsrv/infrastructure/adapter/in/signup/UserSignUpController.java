package br.com.authsrv.infrastructure.adapter.in.signup;

import br.com.authsrv.application.port.in.ISignUpUseCase;
import br.com.authsrv.application.port.in.dto.SignUpOutput;
import br.com.authsrv.infrastructure.adapter.in.signup.dto.SignUpMapper;
import br.com.authsrv.infrastructure.adapter.in.signup.dto.SignUpRequest;
import br.com.authsrv.infrastructure.adapter.in.signup.dto.SignUpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSignUpController implements SwaggerSignUpController {

    private final ISignUpUseCase useCase;
    private final SignUpMapper mapper;

    public UserSignUpController(ISignUpUseCase useCase, SignUpMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<SignUpResponse> signup(SignUpRequest request) {
        SignUpOutput output = useCase.signUp(mapper.toInput(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(output));
    }
}
