package br.com.eventsrv.infrastructure.adapter.out.database.venue.mapper;

import br.com.eventsrv.application.domain.venue.entity.Address;
import br.com.eventsrv.application.domain.venue.entity.EventVenue;
import br.com.eventsrv.infrastructure.adapter.out.database.venue.entity.AddressEntity;
import br.com.eventsrv.infrastructure.adapter.out.database.venue.entity.EventVenueEntity;

public class EventVenueEntityMapper {

    public EventVenueEntity toEntity(EventVenue venue) {
        return new EventVenueEntity(
                venue.id(),
                venue.name(),
                venue.description(),
                venue.capacity(),
                toEntity(venue.address()),
                venue.createdAt(),
                venue.updatedAt()
        );
    }

    public EventVenue toDomain(EventVenueEntity entity) {
        return new EventVenue(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCapacity(),
                toDomain(entity.getAddress()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    private AddressEntity toEntity(Address address) {
        return new AddressEntity(
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
        );
    }

    private Address toDomain(AddressEntity entity) {
        return new Address(
                entity.getId(),
                entity.getStreet(),
                entity.getNumber(),
                entity.getComplement(),
                entity.getNeighborhood(),
                entity.getCity(),
                entity.getState(),
                entity.getCountry(),
                entity.getPostalCode(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
