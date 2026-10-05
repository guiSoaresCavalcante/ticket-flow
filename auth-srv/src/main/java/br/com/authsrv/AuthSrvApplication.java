package br.com.authsrv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuthSrvApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthSrvApplication.class, args);
	}

}
