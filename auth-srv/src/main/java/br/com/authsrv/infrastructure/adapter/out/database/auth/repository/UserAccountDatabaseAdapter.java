package br.com.authsrv.infrastructure.adapter.out.database.auth.repository;

import br.com.authsrv.application.domain.auth.entity.UserAccount;
import br.com.authsrv.application.port.out.IUserAccountRepository;
import br.com.authsrv.infrastructure.adapter.out.database.auth.entity.UserAccountEntity;
import br.com.authsrv.infrastructure.adapter.out.database.auth.mapper.UserAccountEntityMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAccountDatabaseAdapter implements IUserAccountRepository {

    private final UserAccountJpaRepository jpaRepository;
    private final UserAccountEntityMapper mapper;

    public UserAccountDatabaseAdapter(UserAccountJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
        this.mapper = new UserAccountEntityMapper();
    }

    @Override
    public UserAccount save(UserAccount account) {
        UserAccountEntity saved = jpaRepository.save(mapper.toEntity(account));
        return mapper.toDomain(saved);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepository.existsByUsername(username);
    }

    @Override
    public Optional<UserAccount> findByUsername(String username) {
        return jpaRepository.findByUsername(username).map(mapper::toDomain);
    }
}
