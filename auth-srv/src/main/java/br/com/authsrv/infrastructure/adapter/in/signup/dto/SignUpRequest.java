package br.com.authsrv.infrastructure.adapter.in.signup.dto;

public record SignUpRequest(
    String username,
    String password,
    String name,
    String document,
    String profileType,
    String email,
    String phoneNumber) {
}
