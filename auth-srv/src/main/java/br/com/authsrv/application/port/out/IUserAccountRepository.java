package br.com.authsrv.application.port.out;

import br.com.authsrv.application.domain.auth.entity.UserAccount;

import java.util.Optional;

public interface IUserAccountRepository {
    UserAccount save(UserAccount account);
    boolean existsByUsername(String username);
    Optional<UserAccount> findByUsername(String username);
}
