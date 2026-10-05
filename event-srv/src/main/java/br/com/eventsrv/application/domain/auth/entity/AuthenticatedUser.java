package br.com.eventsrv.application.domain.auth.entity;

public record AuthenticatedUser(
        String accountId,
        String username,
        String profileId
) {
}
