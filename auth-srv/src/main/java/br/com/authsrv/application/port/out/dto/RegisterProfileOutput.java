package br.com.authsrv.application.port.out.dto;

public record RegisterProfileOutput(String profileId, String name, String document, String profileType) {
}
