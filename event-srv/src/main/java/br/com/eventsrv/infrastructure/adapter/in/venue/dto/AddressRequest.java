package br.com.eventsrv.infrastructure.adapter.in.venue.dto;

public record AddressRequest(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String country,
        String postalCode
) {
}
