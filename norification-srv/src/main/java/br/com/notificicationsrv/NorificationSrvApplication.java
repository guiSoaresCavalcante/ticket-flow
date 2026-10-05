package br.com.notificicationsrv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NorificationSrvApplication {

	public static void main(String[] args) {
		SpringApplication.run(NorificationSrvApplication.class, args);
	}

}
