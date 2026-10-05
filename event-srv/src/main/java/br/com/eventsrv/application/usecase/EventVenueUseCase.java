package br.com.eventsrv.application.usecase;

import br.com.eventsrv.application.domain.venue.entity.Address;
import br.com.eventsrv.application.domain.venue.entity.EventVenue;
import br.com.eventsrv.application.domain.venue.exceptions.InvalidVenueException;
import br.com.eventsrv.application.port.in.IEventVenueUseCase;
import br.com.eventsrv.application.port.out.IEventVenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventVenueUseCase implements IEventVenueUseCase {

    private final IEventVenueRepository repository;

    public EventVenueUseCase(IEventVenueRepository repository) {
        this.repository = repository;
    }

    @Override
    public EventVenue create(EventVenue venue) {
        validate(venue);
        LocalDateTime now = LocalDateTime.now();
        Address address = venue.address();
        return repository.save(new EventVenue(
                null,
                venue.name(),
                venue.description(),
                venue.capacity(),
                new Address(
                        null,
                        address.street(),
                        address.number(),
                        address.complement(),
                        address.neighborhood(),
                        address.city(),
                        address.state(),
                        address.country(),
                        address.postalCode(),
                        now,
                        now
                ),
                now,
                now
        ));
    }

    @Override
    public List<EventVenue> findAll() {
        return repository.findAll();
    }

    private void validate(EventVenue venue) {
        if (!StringUtils.hasText(venue.name())) {
            throw new InvalidVenueException("name is required");
        }
        if (venue.capacity() != null && venue.capacity() < 0) {
            throw new InvalidVenueException("capacity must not be negative");
        }
        Address address = venue.address();
        if (address == null) {
            throw new InvalidVenueException("address is required");
        }
        if (!StringUtils.hasText(address.street())) {
            throw new InvalidVenueException("address.street is required");
        }
        if (!StringUtils.hasText(address.city())) {
            throw new InvalidVenueException("address.city is required");
        }
        if (!StringUtils.hasText(address.state())) {
            throw new InvalidVenueException("address.state is required");
        }
        if (!StringUtils.hasText(address.country())) {
            throw new InvalidVenueException("address.country is required");
        }
    }
}
