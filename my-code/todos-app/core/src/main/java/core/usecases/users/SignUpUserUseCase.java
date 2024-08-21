package core.usecases.users;

import java.sql.SQLException;

import core.dataaccess.IUsersDataAccess;
import core.dtos.SignUpFormDto;
import core.entities.UserEntity;
import core.exceptions.EmailAlreadyInUseException;
import core.exceptions.InvalidUserException;
import core.services.IPasswordService;

public class SignUpUserUseCase {
    private final IUsersDataAccess usersDataAccess;
    private final IPasswordService passwordService;

    public SignUpUserUseCase(IUsersDataAccess usersDataAccess, IPasswordService passwordService) {
        this.usersDataAccess = usersDataAccess;
        this.passwordService = passwordService;
    }

    public void execute(SignUpFormDto form) throws InvalidUserException, EmailAlreadyInUseException, SQLException {
        UserEntity.validateUser(form);
        System.out.println("[Info] User form is valid");

        UserEntity.checkEmailIsAvailable(form.email, this.usersDataAccess);
        System.out.println("[Info] E-mail is available");

        var passwordHash = UserEntity.hashPassword(form.password, this.passwordService);
        System.out.println("[Info] Password hash created");
    }
}
