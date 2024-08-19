package utils;

import java.sql.Connection;

import core.usecases.users.SignUpUserUseCase;
import dataaccess.UsersDataAccess;
import services.PasswordService;

public class UseCasesFactory {

    public static SignUpUserUseCase getSignUpUserUseCase(Connection connection) {
        var usersDataAccess = new UsersDataAccess(connection);
        var passwordService = new PasswordService();
        return new SignUpUserUseCase(usersDataAccess, passwordService);
    }

}
