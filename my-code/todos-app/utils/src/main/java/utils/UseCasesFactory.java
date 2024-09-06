package utils;

import java.sql.Connection;

import core.usecases.todos.CreateTodoUseCase;
import core.usecases.todos.FindOneTodoUseCase;
import core.usecases.todos.FindAllTodosByUserIdUseCase;
import core.usecases.todos.UpdateTodoUseCase;
import core.usecases.users.SignInUserUseCase;
import core.usecases.users.SignUpUserUseCase;
import dataaccess.TodosDataAccess;
import dataaccess.UsersDataAccess;
import services.JwtService;
import services.PasswordService;

public class UseCasesFactory {

    public static SignUpUserUseCase getSignUpUserUseCase(Connection connection) {
        final var usersDataAccess = new UsersDataAccess(connection);
        final var passwordService = new PasswordService();
        return new SignUpUserUseCase(usersDataAccess, passwordService);
    }

    public static SignInUserUseCase getSignInUserUseCase(Connection connection) {
        final var usersDataAccess = new UsersDataAccess(connection);
        final var passwordService = new PasswordService();
        final var jwtService = new JwtService();
        return new SignInUserUseCase(usersDataAccess, passwordService, jwtService);
    }

    public static CreateTodoUseCase getCreateTodoUseCase(Connection connection) {
        final var usersDataAccess = new UsersDataAccess(connection);
        final var todosDataAccess = new TodosDataAccess(connection);
        return new CreateTodoUseCase(usersDataAccess, todosDataAccess);
    }

    public static FindOneTodoUseCase getFindOneTodoUseCase(Connection connection) {
        final var usersDataAccess = new UsersDataAccess(connection);
        final var todosDataAccess = new TodosDataAccess(connection);
        return new FindOneTodoUseCase(usersDataAccess, todosDataAccess);
    }

    public static FindAllTodosByUserIdUseCase getFindAllTodosByUserIdUseCase(Connection connection) {
        final var usersDataAccess = new UsersDataAccess(connection);
        final var todosDataAccess = new TodosDataAccess(connection);
        return new FindAllTodosByUserIdUseCase(usersDataAccess, todosDataAccess);
    }

    public static UpdateTodoUseCase getUpdateTodoUseCase(Connection connection) {
        final var usersDataAccess = new UsersDataAccess(connection);
        final var todosDataAccess = new TodosDataAccess(connection);
        return new UpdateTodoUseCase(usersDataAccess, todosDataAccess);
    }

}
