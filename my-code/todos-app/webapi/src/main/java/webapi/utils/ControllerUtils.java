package webapi.utils;

import org.springframework.http.ResponseEntity;

import core.utils.EnvUtils;
import services.JwtService;
import webapi.exceptions.UnauthorizedRequestException;

public class ControllerUtils {
    public static String getUserIdFromToken(String bearerToken) throws UnauthorizedRequestException {
        if (bearerToken == null || bearerToken.isEmpty()) {
            throw new UnauthorizedRequestException();
        }

        final var tokenSplited = bearerToken.split(" ");
        if (! tokenSplited[0].equals("Bearer") || tokenSplited[1].isEmpty()) {
            throw new UnauthorizedRequestException();
        }
        final var token = tokenSplited[1];

        try {
            final var jwtSecret = EnvUtils.getJwtSecretKey();
            final var jwtService = new JwtService();
            final var userId = jwtService.getUserIdFromToken(token, jwtSecret);
            return userId;
        } catch (Exception e) {
            throw new UnauthorizedRequestException();
        }
    }

    public static ResponseEntity<Object> getUnauthorizedResponse() {
        return ResponseEntity.status(401).body(new UnauthorizedRequestException().getMessage());
    }
}
