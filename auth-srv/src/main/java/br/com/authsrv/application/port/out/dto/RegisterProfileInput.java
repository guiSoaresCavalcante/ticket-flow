package br.com.authsrv.application.port.out.dto;

public record RegisterProfileInput(
    String name,
    String document,
    String profileType,
    String email,
    String phoneNumber) {
}
