package br.com.notificicationsrv.infrastructure.adapter.out.client.event;

import br.com.notificicationsrv.infrastructure.adapter.out.client.event.dto.EventSrvEventResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "event-srv", url = "${event-srv.base-url}", configuration = EventSrvFeignConfig.class)
public interface EventSrvFeignClient {

    @GetMapping("/events/{eventId}")
    EventSrvEventResponse findById(@PathVariable("eventId") UUID eventId);
}
