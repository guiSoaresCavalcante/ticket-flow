package br.com.notificicationsrv.infrastructure.adapter.in.messaging.event;

import br.com.notificicationsrv.application.port.in.ISendNotificationUseCase;
import br.com.notificicationsrv.application.port.in.dto.SendNotificationInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventAttendanceKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(EventAttendanceKafkaConsumer.class);

    private final ISendNotificationUseCase useCase;

    public EventAttendanceKafkaConsumer(ISendNotificationUseCase useCase) {
        this.useCase = useCase;
    }

    @KafkaListener(topics = "${kafka.topics.event-attendance}")
    public void consume(EventAttendanceMessage message) {
        if (message == null || message.eventId() == null || message.profileId() == null) {
            log.warn("Discarding invalid event attendance message: {}", message);
            return;
        }
        useCase.send(new SendNotificationInput(message.eventId(), message.profileId()));
    }
}
