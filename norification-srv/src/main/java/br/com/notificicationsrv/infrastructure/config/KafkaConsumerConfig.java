package br.com.notificicationsrv.infrastructure.config;

import br.com.notificicationsrv.application.domain.notification.exceptions.AttendeeNotFoundException;
import br.com.notificicationsrv.application.domain.notification.exceptions.EventNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConsumerConfig {

    private static final long RETRY_INTERVAL_MS = 1000;
    private static final long MAX_RETRIES = 3;

    @Bean
    public DefaultErrorHandler kafkaErrorHandler() {
        DefaultErrorHandler handler = new DefaultErrorHandler(new FixedBackOff(RETRY_INTERVAL_MS, MAX_RETRIES));
        handler.addNotRetryableExceptions(AttendeeNotFoundException.class, EventNotFoundException.class);
        return handler;
    }
}
