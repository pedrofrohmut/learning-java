package core.services;

public interface IJwtService {
    String createSignInToken(String userId, long duration, String secretKey);
    String getUserIdFromToken(String token, String secretKey);
}
