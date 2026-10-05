package br.com.authsrv.application.domain.auth.entity;

public record UserAccount(
        String accountId,
        String username,
        String passwordHash,
        String profileId,
        String name,
        String document,
        String profileType
) {
}
