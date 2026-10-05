package br.com.authsrv.infrastructure.adapter.in.signin;

import br.com.authsrv.application.port.in.ISignInUseCase;
import br.com.authsrv.application.port.in.dto.SignInOutput;
import br.com.authsrv.infrastructure.adapter.in.signin.dto.SignInMapper;
import br.com.authsrv.infrastructure.adapter.in.signin.dto.SignInRequest;
import br.com.authsrv.infrastructure.adapter.in.signin.dto.SignInResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSignInController implements SwaggerSignInController {

    private final ISignInUseCase useCase;
    private final SignInMapper mapper;

    public UserSignInController(ISignInUseCase useCase, SignInMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<SignInResponse> signin(SignInRequest request) {
        SignInOutput output = useCase.signIn(mapper.toInput(request));
        return ResponseEntity.ok(mapper.toResponse(output));
    }
}
