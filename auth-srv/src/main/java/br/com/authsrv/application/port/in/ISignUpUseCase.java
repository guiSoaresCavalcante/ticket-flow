package br.com.authsrv.application.port.in;

import br.com.authsrv.application.port.in.dto.SignUpInput;
import br.com.authsrv.application.port.in.dto.SignUpOutput;

public interface ISignUpUseCase {
    SignUpOutput signUp(SignUpInput input);
}
