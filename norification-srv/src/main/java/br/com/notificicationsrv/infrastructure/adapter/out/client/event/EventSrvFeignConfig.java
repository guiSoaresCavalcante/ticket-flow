package br.com.notificicationsrv.infrastructure.adapter.out.client.event;

import feign.RequestInterceptor;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

public class EventSrvFeignConfig {

    private static final String SERVICE_NAME = "notification-srv";
    private static final Duration TOKEN_TTL = Duration.ofMinutes(5);

    @Bean
    public RequestInterceptor serviceTokenInterceptor(@Value("${jwt.secret}") String secret) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return template -> {
            Date now = new Date();
            String token = Jwts.builder()
                    .subject(SERVICE_NAME)
                    .claim("username", SERVICE_NAME)
                    .issuedAt(now)
                    .expiration(new Date(now.getTime() + TOKEN_TTL.toMillis()))
                    .signWith(key)
                    .compact();
            template.header(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        };
    }
}
