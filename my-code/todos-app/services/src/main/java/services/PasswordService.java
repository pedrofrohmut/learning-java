package services;

import de.mkammerer.argon2.Argon2Factory;

import org.springframework.stereotype.Component;

import core.services.IPasswordService;

@Component
public class PasswordService implements IPasswordService {

	@Override
	public String hashPassword(String password) {
        final var argon2 = Argon2Factory.create();
        final var hash = argon2.hash(10, 65536, 1, password.toCharArray());
        argon2.wipeArray(password.toCharArray()); // Clear the password array
        return hash;
	}

	@Override
	public boolean verifyPassword(String password, String passwordHash) {
        final var argon2 = Argon2Factory.create();
        return argon2.verify(passwordHash, password.toCharArray());
	}

}
