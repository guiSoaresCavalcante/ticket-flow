package br.com.notificicationsrv.application.usecase;

import br.com.notificicationsrv.application.domain.notification.entity.AttendanceNotification;
import br.com.notificicationsrv.application.domain.notification.entity.Attendee;
import br.com.notificicationsrv.application.domain.notification.entity.EventDetails;
import br.com.notificicationsrv.application.port.in.ISendNotificationUseCase;
import br.com.notificicationsrv.application.port.in.dto.SendNotificationInput;
import br.com.notificicationsrv.application.port.out.IEventInfoPort;
import br.com.notificicationsrv.application.port.out.INotificationSender;
import br.com.notificicationsrv.application.port.out.IUserInfoPort;
import org.springframework.stereotype.Service;

@Service
public class SendNotificationUseCase implements ISendNotificationUseCase {

    private final IUserInfoPort userInfoPort;
    private final IEventInfoPort eventInfoPort;
    private final INotificationSender notificationSender;

    public SendNotificationUseCase(IUserInfoPort userInfoPort, IEventInfoPort eventInfoPort,
                                   INotificationSender notificationSender) {
        this.userInfoPort = userInfoPort;
        this.eventInfoPort = eventInfoPort;
        this.notificationSender = notificationSender;
    }

    @Override
    public void send(SendNotificationInput input) {
        Attendee attendee = userInfoPort.findByProfileId(input.profileId());
        EventDetails event = eventInfoPort.findById(input.eventId());
        notificationSender.send(new AttendanceNotification(attendee, event));
    }
}
