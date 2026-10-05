package br.com.eventsrv.infrastructure.adapter.out.security;

import br.com.eventsrv.application.domain.auth.entity.AuthenticatedUser;
import br.com.eventsrv.application.domain.auth.exceptions.InvalidTokenException;
import br.com.eventsrv.application.port.out.ITokenValidator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JwtTokenValidator implements ITokenValidator {

    private final SecretKey key;

    public JwtTokenValidator(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public AuthenticatedUser validate(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return new AuthenticatedUser(
                    claims.getSubject(),
                    claims.get("username", String.class),
                    claims.get("profileId", String.class)
            );
        } catch (JwtException | IllegalArgumentException ex) {
            throw new InvalidTokenException("Invalid or expired token", ex);
        }
    }
}
