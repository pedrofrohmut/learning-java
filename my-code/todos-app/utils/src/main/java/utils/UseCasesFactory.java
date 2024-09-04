package utils;

import java.sql.Connection;

import core.usecases.todos.CreateTodoUseCase;
import core.usecases.users.SignInUserUseCase;
import core.usecases.users.SignUpUserUseCase;
import dataaccess.TodosDataAccess;
import dataaccess.UsersDataAccess;
import services.JwtService;
import services.PasswordService;

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

    public static CreateTodoUseCase getCreateTodoUseCase(Connection connection) {
        var usersDataAccess = new UsersDataAccess(connection);
        var todosDataAccess = new TodosDataAccess(connection);
        return new CreateTodoUseCase(usersDataAccess, todosDataAccess);
    }

}
