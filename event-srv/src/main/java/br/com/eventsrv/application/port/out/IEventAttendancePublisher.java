package br.com.eventsrv.application.port.out;

import br.com.eventsrv.application.domain.event.entity.EventAttendance;

public interface IEventAttendancePublisher {

    void publish(EventAttendance attendance);
}
