package br.com.notificicationsrv.application.port.out;

import br.com.notificicationsrv.application.domain.notification.entity.AttendanceNotification;

public interface INotificationSender {

    void send(AttendanceNotification notification);
}
