package br.com.notificicationsrv.application.port.out;

import br.com.notificicationsrv.application.domain.notification.entity.EventDetails;

import java.util.UUID;

public interface IEventInfoPort {

    EventDetails findById(UUID eventId);
}
