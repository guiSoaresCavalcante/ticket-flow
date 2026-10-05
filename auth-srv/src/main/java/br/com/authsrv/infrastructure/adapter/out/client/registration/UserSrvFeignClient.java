package br.com.authsrv.infrastructure.adapter.out.client.registration;

import br.com.authsrv.infrastructure.adapter.out.client.registration.dto.UserSrvRegistrationRequest;
import br.com.authsrv.infrastructure.adapter.out.client.registration.dto.UserSrvRegistrationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-srv", url = "${user-srv.base-url}")
public interface UserSrvFeignClient {

    @PostMapping("/users/registration")
    UserSrvRegistrationResponse register(@RequestBody UserSrvRegistrationRequest request);
}
