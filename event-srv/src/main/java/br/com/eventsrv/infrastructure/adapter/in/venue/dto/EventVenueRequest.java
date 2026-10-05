package br.com.eventsrv.infrastructure.adapter.in.venue.dto;

public record EventVenueRequest(
        String name,
        String description,
        Integer capacity,
        AddressRequest address
) {
}
