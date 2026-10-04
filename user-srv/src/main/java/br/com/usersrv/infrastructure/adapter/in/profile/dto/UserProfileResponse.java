package br.com.usersrv.infrastructure.adapter.in.profile.dto;

public record UserProfileResponse(
        String profileId,
        String accountId,
        String name,
        String email
) {
}
