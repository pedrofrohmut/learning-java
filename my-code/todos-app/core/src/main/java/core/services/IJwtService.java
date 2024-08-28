package core.services;

public interface IJwtService {
    String createSignInToken(String userId);
}
