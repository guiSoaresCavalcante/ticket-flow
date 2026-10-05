package br.com.usersrv.infrastructure.adapter.in.profile.dto;

public record UserRegistrationRequest(
        String name,
        String document,
        String profileType,
        String email,
        String phoneNumber
) {
}
