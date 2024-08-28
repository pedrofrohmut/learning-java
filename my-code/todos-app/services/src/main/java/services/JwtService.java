package services;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;
import core.services.IJwtService;
import core.utils.Constants;

public class JwtService implements IJwtService {

	@Override
	public String createSignInToken(String userId) {
        var now = new Date();
        var expiration = new Date(now.getTime() + Constants.jwtDuration);

        var secretKey = Jwts.SIG.HS256.key().build();

        var token =
            Jwts.builder()
                .claim("userId", userId)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();

        return token;
	}
}
