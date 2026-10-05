package br.com.authsrv.application.port.in;

import br.com.authsrv.application.port.in.dto.SignInInput;
import br.com.authsrv.application.port.in.dto.SignInOutput;

public interface ISignInUseCase {
    SignInOutput signIn(SignInInput input);
}
