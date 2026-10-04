package br.com.usersrv.infrastructure.adapter.in.profile.dto;

public record UserProfileResponse(
        String profileId,
        String name,
        String document,
        String profileType
) {
}
