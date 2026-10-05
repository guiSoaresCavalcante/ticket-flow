package br.com.authsrv.application.port.in.dto;

public record SignUpInput(String username, String password, String name, String document, String profileType) {
}
