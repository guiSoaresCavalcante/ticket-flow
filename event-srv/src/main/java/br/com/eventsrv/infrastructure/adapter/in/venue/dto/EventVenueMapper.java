package br.com.eventsrv.infrastructure.adapter.in.venue.dto;

import br.com.eventsrv.application.domain.venue.entity.Address;
import br.com.eventsrv.application.domain.venue.entity.EventVenue;
import org.springframework.stereotype.Component;

@Component
public class EventVenueMapper {

    public EventVenue toDomain(EventVenueRequest request) {
        AddressRequest address = request.address();
        Address domainAddress = address == null ? null : new Address(
                null,
                address.street(),
                address.number(),
                address.complement(),
                address.neighborhood(),
                address.city(),
                address.state(),
                address.country(),
                address.postalCode(),
                null,
                null
        );
        return new EventVenue(null, request.name(), request.description(), request.capacity(), domainAddress, null, null);
    }

    public EventVenueResponse toResponse(EventVenue venue) {
        Address address = venue.address();
        return new EventVenueResponse(
                venue.id(),
                venue.name(),
                venue.description(),
                venue.capacity(),
                new AddressResponse(
                        address.id(),
                        address.street(),
                        address.number(),
                        address.complement(),
                        address.neighborhood(),
                        address.city(),
                        address.state(),
                        address.country(),
                        address.postalCode(),
                        address.createdAt(),
                        address.updatedAt()
                ),
                venue.createdAt(),
                venue.updatedAt()
        );
    }
}
