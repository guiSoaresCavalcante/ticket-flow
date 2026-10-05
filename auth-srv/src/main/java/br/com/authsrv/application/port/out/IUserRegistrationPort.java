package br.com.authsrv.application.port.out;

import br.com.authsrv.application.port.out.dto.RegisterProfileInput;
import br.com.authsrv.application.port.out.dto.RegisterProfileOutput;

public interface IUserRegistrationPort {
    RegisterProfileOutput register(RegisterProfileInput input);
}
