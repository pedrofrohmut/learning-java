package core.usecases.users;

import core.dataaccess.IUsersDataAccess;
import core.services.IPasswordService;

public class SignUpUserUseCase {
    private final IUsersDataAccess usersDataAccess;
    private final IPasswordService passwordService;

    public SignUpUserUseCase(IUsersDataAccess usersDataAccess, IPasswordService passwordService) {
        this.usersDataAccess = usersDataAccess;
        this.passwordService = passwordService;
    }
}
