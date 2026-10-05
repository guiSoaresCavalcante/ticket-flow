package br.com.authsrv.application.port.in.dto;

public record SignInOutput(String accessToken, String tokenType, long expiresIn) {
}
