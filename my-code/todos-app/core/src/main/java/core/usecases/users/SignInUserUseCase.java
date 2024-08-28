package core.usecases.users;

import java.sql.SQLException;

import core.dataaccess.IUsersDataAccess;
import core.dtos.SignInFormDto;
import core.dtos.SignedUserDto;
import core.entities.UserEntity;
import core.exceptions.InvalidUserException;
import core.exceptions.PasswordDontMatchException;
import core.exceptions.UserNotFoundException;
import core.services.IJwtService;
import core.services.IPasswordService;

public class SignInUserUseCase {
    private final IUsersDataAccess usersDataAccess;
    private final IPasswordService passwordService;
    private final IJwtService jwtService;

    public SignInUserUseCase(IUsersDataAccess usersDataAccess, IPasswordService passwordService, IJwtService jwtService) {
        this.usersDataAccess = usersDataAccess;
        this.passwordService = passwordService;
        this.jwtService = jwtService;
    }

    public SignedUserDto execute(SignInFormDto form)
            throws InvalidUserException, UserNotFoundException, PasswordDontMatchException, SQLException {
        UserEntity.validateUser(form);
        System.out.println("[Info] User form is valid");

        var userDb = UserEntity.findUserByEmail(form.email, this.usersDataAccess);
        System.out.println("[Info] User found by e-mail");

        UserEntity.verifyPassword(form.password, userDb.passwordHash, this.passwordService);
        System.out.println("[Info] User password and password hash match each other");

        var signInToken = UserEntity.createSignInToken(userDb.id, this.jwtService);
        System.out.println("[Info] Signin Token generated");

        return new SignedUserDto(userDb.id, userDb.name, userDb.email, signInToken);
    }
}
