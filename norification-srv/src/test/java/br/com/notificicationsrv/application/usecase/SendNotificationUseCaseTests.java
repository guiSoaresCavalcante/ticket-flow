package br.com.notificicationsrv.application.usecase;

import br.com.notificicationsrv.application.domain.notification.entity.AttendanceNotification;
import br.com.notificicationsrv.application.domain.notification.entity.Attendee;
import br.com.notificicationsrv.application.domain.notification.entity.EventDetails;
import br.com.notificicationsrv.application.domain.notification.exceptions.EventNotFoundException;
import br.com.notificicationsrv.application.port.in.dto.SendNotificationInput;
import br.com.notificicationsrv.application.port.out.IEventInfoPort;
import br.com.notificicationsrv.application.port.out.INotificationSender;
import br.com.notificicationsrv.application.port.out.IUserInfoPort;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SendNotificationUseCaseTests {

    private final IUserInfoPort userInfoPort = mock(IUserInfoPort.class);
    private final IEventInfoPort eventInfoPort = mock(IEventInfoPort.class);
    private final INotificationSender sender = mock(INotificationSender.class);
    private final SendNotificationUseCase useCase = new SendNotificationUseCase(userInfoPort, eventInfoPort, sender);

    private final UUID eventId = UUID.randomUUID();
    private final UUID profileId = UUID.randomUUID();

    @Test
    void sendsNotificationWithAttendeeAndEventData() {
        Attendee attendee = new Attendee(profileId, "Maria", "maria@mail.com", "5511999999999");
        EventDetails event = new EventDetails(eventId, "Show", "desc", LocalDateTime.now(), null);
        when(userInfoPort.findByProfileId(profileId)).thenReturn(attendee);
        when(eventInfoPort.findById(eventId)).thenReturn(event);

        useCase.send(new SendNotificationInput(eventId, profileId));

        verify(sender).send(new AttendanceNotification(attendee, event));
    }

    @Test
    void doesNotSendWhenEventIsNotFound() {
        when(userInfoPort.findByProfileId(profileId))
                .thenReturn(new Attendee(profileId, "Maria", "maria@mail.com", null));
        when(eventInfoPort.findById(eventId)).thenThrow(new EventNotFoundException(eventId));

        assertThrows(EventNotFoundException.class,
                () -> useCase.send(new SendNotificationInput(eventId, profileId)));

        verify(sender, never()).send(org.mockito.ArgumentMatchers.any());
    }
}
