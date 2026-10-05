package br.com.notificicationsrv.infrastructure.adapter.out.client.event;

import br.com.notificicationsrv.application.domain.notification.entity.EventDetails;
import br.com.notificicationsrv.application.domain.notification.exceptions.EventNotFoundException;
import br.com.notificicationsrv.application.domain.notification.exceptions.NotificationDataUnavailableException;
import br.com.notificicationsrv.application.port.out.IEventInfoPort;
import br.com.notificicationsrv.infrastructure.adapter.out.client.event.dto.EventSrvEventResponse;
import feign.FeignException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EventInfoClientAdapter implements IEventInfoPort {

    private final EventSrvFeignClient feignClient;

    public EventInfoClientAdapter(EventSrvFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public EventDetails findById(UUID eventId) {
        try {
            EventSrvEventResponse response = feignClient.findById(eventId);
            return new EventDetails(response.id(), response.name(), response.description(),
                                    response.startAt(), response.endAt());
        } catch (FeignException.NotFound ex) {
            throw new EventNotFoundException(eventId);
        } catch (FeignException ex) {
            throw new NotificationDataUnavailableException("event-srv", ex);
        }
    }
}
