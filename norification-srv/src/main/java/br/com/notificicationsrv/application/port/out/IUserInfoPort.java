package br.com.notificicationsrv.application.port.out;

import br.com.notificicationsrv.application.domain.notification.entity.Attendee;

import java.util.UUID;

public interface IUserInfoPort {

    Attendee findByProfileId(UUID profileId);
}
