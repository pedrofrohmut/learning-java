package services;

import java.util.Date;

import org.springframework.stereotype.Component;

import core.services.IJwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService implements IJwtService {

    @Override
    public String createSignInToken(String userId, long duration, String secretKey) {
        final var now = new Date();
        final var expiration = new Date(now.getTime() + duration);

        final var key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        final var token = Jwts.builder()
                .claim("userId", userId)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(key)
                .compact();
        return token;
    }

    @Override
    public String getUserIdFromToken(String token, String secretKey) {
        final var key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        final var parsed = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
        final var payload = parsed.getPayload();
        final var userId = payload.get("userId", String.class);
        return userId;
    }

}
