package br.com.authsrv.infrastructure.adapter.out.database.auth.mapper;

import br.com.authsrv.application.domain.auth.entity.UserAccount;
import br.com.authsrv.infrastructure.adapter.out.database.auth.entity.UserAccountEntity;

import java.util.UUID;

public class UserAccountEntityMapper {

    public UserAccountEntity toEntity(UserAccount account) {
        UUID id = account.accountId() != null ? UUID.fromString(account.accountId()) : null;
        return new UserAccountEntity(id, account.username(), account.passwordHash(), account.profileId(),
                account.name(), account.document(), account.profileType());
    }

    public UserAccount toDomain(UserAccountEntity entity) {
        return new UserAccount(entity.getId().toString(), entity.getUsername(), entity.getPasswordHash(),
                entity.getProfileId(), entity.getName(), entity.getDocument(), entity.getProfileType());
    }
}
