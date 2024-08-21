package services;

import de.mkammerer.argon2.Argon2Factory;

import core.services.IPasswordService;

public class PasswordService implements IPasswordService {

	@Override
	public String hashPassword(String password) {
        var argon2 = Argon2Factory.create();
        var hash = argon2.hash(10, 65536, 1, password.toCharArray());
        argon2.wipeArray(password.toCharArray()); // Clear the password array
        return hash;
	}

	@Override
	public boolean verifyPassword(String password, String passwordHash) {
        var argon2 = Argon2Factory.create();
        return argon2.verify(passwordHash, password.toCharArray());
	}

}
