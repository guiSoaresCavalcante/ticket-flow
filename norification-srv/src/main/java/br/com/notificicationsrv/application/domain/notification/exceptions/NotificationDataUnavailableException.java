package br.com.notificicationsrv.application.domain.notification.exceptions;

public class NotificationDataUnavailableException extends RuntimeException {

    public NotificationDataUnavailableException(String source, Throwable cause) {
        super("Could not retrieve data from " + source, cause);
    }
}
