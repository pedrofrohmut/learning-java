package utils;

import java.sql.Connection;

import core.usecases.users.SignUpUserUseCase;
import core.usecases.users.SignInUserUseCase;
import dataaccess.UsersDataAccess;
import services.PasswordService;
import services.JwtService;

public class UseCasesFactory {

    public static SignUpUserUseCase getSignUpUserUseCase(Connection connection) {
        var usersDataAccess = new UsersDataAccess(connection);
        var passwordService = new PasswordService();
        return new SignUpUserUseCase(usersDataAccess, passwordService);
    }

    public static SignInUserUseCase getSignInUserUseCase(Connection connection) {
        var usersDataAccess = new UsersDataAccess(connection);
        var passwordService = new PasswordService();
        var jwtService = new JwtService();
        return new SignInUserUseCase(usersDataAccess, passwordService, jwtService);
    }

}
