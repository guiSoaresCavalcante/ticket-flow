package br.com.usersrv.application.domain.profile;

public record UserProfile(
        String profileId,
        String name,
        String document,
        String profileType,
        String email,
        String phoneNumber
) {
}
