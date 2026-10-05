package br.com.authsrv.infrastructure.adapter.out.client.registration;

import br.com.authsrv.application.domain.auth.exceptions.UserRegistrationException;
import br.com.authsrv.application.port.out.IUserRegistrationPort;
import br.com.authsrv.application.port.out.dto.RegisterProfileInput;
import br.com.authsrv.application.port.out.dto.RegisterProfileOutput;
import br.com.authsrv.infrastructure.adapter.out.client.registration.dto.UserSrvRegistrationRequest;
import br.com.authsrv.infrastructure.adapter.out.client.registration.dto.UserSrvRegistrationResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationClientAdapter implements IUserRegistrationPort {

    private final UserSrvFeignClient feignClient;

    public UserRegistrationClientAdapter(UserSrvFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public RegisterProfileOutput register(RegisterProfileInput input) {
        try {
            UserSrvRegistrationResponse response = feignClient.register(
                    new UserSrvRegistrationRequest(input.name(), input.document(), input.profileType()));
            return new RegisterProfileOutput(response.profileId(), response.name(), response.document(), response.profileType());
        } catch (FeignException ex) {
            int statusCode = ex.status() > 0 ? ex.status() : HttpStatus.INTERNAL_SERVER_ERROR.value();
            throw new UserRegistrationException(ex.contentUTF8(), statusCode);
        }
    }
}
