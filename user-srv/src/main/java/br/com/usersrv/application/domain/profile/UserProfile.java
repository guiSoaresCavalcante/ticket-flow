package br.com.usersrv.application.domain.profile;

public record UserProfile(
        String profileId,
        String accountId,
        String name,
        String document
) {
}
