package br.com.eventsrv.infrastructure.adapter.out.messaging.event;

import br.com.eventsrv.application.domain.event.entity.EventAttendance;
import br.com.eventsrv.application.domain.event.exceptions.EventAttendancePublishException;
import br.com.eventsrv.application.port.out.IEventAttendancePublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class EventAttendanceKafkaPublisher implements IEventAttendancePublisher {

    private static final long SEND_TIMEOUT_SECONDS = 10;

    private final KafkaTemplate<String, EventAttendanceMessage> kafkaTemplate;
    private final String topic;

    public EventAttendanceKafkaPublisher(KafkaTemplate<String, EventAttendanceMessage> kafkaTemplate,
                                         @Value("${kafka.topics.event-attendance}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void publish(EventAttendance attendance) {
        EventAttendanceMessage message = new EventAttendanceMessage(attendance.eventId(), attendance.profileId());
        try {
            kafkaTemplate.send(topic, attendance.eventId().toString(), message)
                    .get(SEND_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new EventAttendancePublishException(attendance.eventId(), ex);
        } catch (ExecutionException | TimeoutException ex) {
            throw new EventAttendancePublishException(attendance.eventId(), ex);
        }
    }
}
