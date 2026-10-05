package br.com.notificicationsrv.infrastructure.adapter.out.notification;

import br.com.notificicationsrv.application.domain.notification.entity.AttendanceNotification;
import br.com.notificicationsrv.application.port.out.INotificationSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogNotificationSender implements INotificationSender {

    private static final Logger log = LoggerFactory.getLogger(LogNotificationSender.class);

    @Override
    public void send(AttendanceNotification notification) {
        log.info("Notification sent to {} (email: {}, phone: {}): attendance confirmed for event '{}' [{}] starting at {}",
                notification.attendee().name(),
                notification.attendee().email(),
                notification.attendee().phoneNumber(),
                notification.event().name(),
                notification.event().id(),
                notification.event().startAt());
    }
}
