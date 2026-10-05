package br.com.eventsrv.application.port.out;

import br.com.eventsrv.application.domain.auth.entity.AuthenticatedUser;

public interface ITokenValidator {

    AuthenticatedUser validate(String token);
}
