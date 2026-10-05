package br.com.notificicationsrv.infrastructure.adapter.out.client.user.dto;

import java.util.UUID;

public record UserSrvProfileResponse(
        UUID profileId,
        String name,
        String email,
        String phoneNumber
) {
}
