package br.com.authsrv.infrastructure.adapter.out.security;

import br.com.authsrv.application.domain.auth.entity.UserAccount;
import br.com.authsrv.application.port.out.ITokenProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenProvider implements ITokenProvider {

    private final SecretKey key;
    private final long expirationSeconds;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret,
                             @Value("${jwt.expiration-seconds}") long expirationSeconds) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationSeconds = expirationSeconds;
    }

    @Override
    public String generateToken(UserAccount account) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(account.accountId())
                .claim("accountId", account.accountId())
                .claim("username", account.username())
                .claim("profileId", account.profileId())
                .claim("name", account.name())
                .claim("document", account.document())
                .claim("profileType", account.profileType())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(expirationSeconds)))
                .signWith(key)
                .compact();
    }

    @Override
    public long getExpirationSeconds() {
        return expirationSeconds;
    }
}
