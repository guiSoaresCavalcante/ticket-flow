package br.com.notificicationsrv.infrastructure.adapter.out.client.user;

import br.com.notificicationsrv.application.domain.notification.entity.Attendee;
import br.com.notificicationsrv.application.domain.notification.exceptions.AttendeeNotFoundException;
import br.com.notificicationsrv.application.domain.notification.exceptions.NotificationDataUnavailableException;
import br.com.notificicationsrv.application.port.out.IUserInfoPort;
import br.com.notificicationsrv.infrastructure.adapter.out.client.user.dto.UserSrvProfileResponse;
import feign.FeignException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserInfoClientAdapter implements IUserInfoPort {

    private final UserSrvFeignClient feignClient;

    public UserInfoClientAdapter(UserSrvFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public Attendee findByProfileId(UUID profileId) {
        try {
            UserSrvProfileResponse response = feignClient.findByProfileId(profileId);
            return new Attendee(profileId, response.name(), response.email(), response.phoneNumber());
        } catch (FeignException.NotFound ex) {
            throw new AttendeeNotFoundException(profileId);
        } catch (FeignException ex) {
            throw new NotificationDataUnavailableException("user-srv", ex);
        }
    }
}
