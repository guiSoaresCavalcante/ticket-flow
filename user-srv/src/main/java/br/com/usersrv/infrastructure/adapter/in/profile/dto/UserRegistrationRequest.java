package br.com.usersrv.infrastructure.adapter.in.profile.dto;

public record UserRegistrationRequest(
        String accountId,
        String name,
        String document
) {
}
