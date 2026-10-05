package br.com.notificicationsrv.application.domain.notification.entity;

public record AttendanceNotification(
        Attendee attendee,
        EventDetails event
) {
}
