package core.usecases.users;

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

    public void execute(SignUpFormDto form) throws InvalidUserException, EmailAlreadyInUseException {
        UserEntity.validateUser(form);
        System.out.println("[Info] User form is valid");
    }
}
