package core.services;

public interface IPasswordService {
    String hashPassword(String password);
    boolean verifyPassword(String password, String passwordHash);
}
