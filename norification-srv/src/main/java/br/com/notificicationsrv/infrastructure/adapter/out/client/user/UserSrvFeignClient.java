package br.com.notificicationsrv.infrastructure.adapter.out.client.user;

import br.com.notificicationsrv.infrastructure.adapter.out.client.user.dto.UserSrvProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "user-srv", url = "${user-srv.base-url}")
public interface UserSrvFeignClient {

    @GetMapping("/users/{profileId}")
    UserSrvProfileResponse findByProfileId(@PathVariable("profileId") UUID profileId);
}
