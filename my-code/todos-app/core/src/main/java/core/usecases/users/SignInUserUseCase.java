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

public class SignInUserUseCase {
    private final IUsersDataAccess usersDataAccess;
    private final IJwtService jwtService;

    public SignInUserUseCase(IUsersDataAccess usersDataAccess, IJwtService jwtService) {
        this.usersDataAccess = usersDataAccess;
        this.jwtService = jwtService;
    }

    public SignedUserDto execute(SignInFormDto form)
            throws InvalidUserException, UserNotFoundException, PasswordDontMatchException, SQLException {
        UserEntity.validateUser(form);
        System.out.println("[Info] User form is valid");

        return null;
    }
}
