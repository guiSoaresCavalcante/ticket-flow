package br.com.authsrv.application.port.out;

import br.com.authsrv.application.domain.auth.entity.UserAccount;

public interface ITokenProvider {
    String generateToken(UserAccount account);
    long getExpirationSeconds();
}
