package br.com.authsrv.infrastructure.adapter.in.signin.dto;

public record SignInResponse(String accessToken, String tokenType, long expiresIn) {
}
