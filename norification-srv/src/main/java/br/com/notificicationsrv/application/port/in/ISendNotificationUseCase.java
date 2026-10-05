package br.com.notificicationsrv.application.port.in;

import br.com.notificicationsrv.application.port.in.dto.SendNotificationInput;

public interface ISendNotificationUseCase {

    void send(SendNotificationInput input);
}
