package utils;

import java.sql.Connection;

import javax.sql.DataSource;

import core.usecases.todos.CreateTodoUseCase;
import core.usecases.todos.DeleteTodoUseCase;
import core.usecases.todos.FindAllTodosByUserIdUseCase;
import core.usecases.todos.FindOneTodoUseCase;
import core.usecases.todos.SetTodoIsDoneUseCase;
import core.usecases.todos.SetTodoIsNotDoneUseCase;
import core.usecases.todos.SimplifiedDeleteTodoUseCase;
import core.usecases.todos.ToggleTodoUseCase;
import core.usecases.todos.UpdateTodoUseCase;
import core.usecases.users.SignInUserUseCase;
import core.usecases.users.SignUpUserUseCase;
import dataaccess.TodosDataAccess;
import dataaccess.UsersDataAccess;
import services.JwtService;
import services.PasswordService;

public class UseCasesFactory {

    public static SignUpUserUseCase getSignUpUserUseCase(DataSource connection) {
        final var usersDataAccess = new UsersDataAccess(connection);
        final var passwordService = new PasswordService();
        return new SignUpUserUseCase(usersDataAccess, passwordService);
    }

    public static SignInUserUseCase getSignInUserUseCase(DataSource connection) {
        final var usersDataAccess = new UsersDataAccess(connection);
        final var passwordService = new PasswordService();
        final var jwtService = new JwtService();
        return new SignInUserUseCase(usersDataAccess, passwordService, jwtService);
    }

    public static CreateTodoUseCase getCreateTodoUseCase(DataSource connection) {
        final var usersDataAccess = new UsersDataAccess(connection);
        final var todosDataAccess = new TodosDataAccess(connection);
        return new CreateTodoUseCase(usersDataAccess, todosDataAccess);
    }

    public static FindOneTodoUseCase getFindOneTodoUseCase(DataSource connection) {
        final var usersDataAccess = new UsersDataAccess(connection);
        final var todosDataAccess = new TodosDataAccess(connection);
        return new FindOneTodoUseCase(usersDataAccess, todosDataAccess);
    }

    public static FindAllTodosByUserIdUseCase getFindAllTodosByUserIdUseCase(DataSource connection) {
        final var usersDataAccess = new UsersDataAccess(connection);
        final var todosDataAccess = new TodosDataAccess(connection);
        return new FindAllTodosByUserIdUseCase(usersDataAccess, todosDataAccess);
    }

    public static UpdateTodoUseCase getUpdateTodoUseCase(DataSource connection) {
        final var usersDataAccess = new UsersDataAccess(connection);
        final var todosDataAccess = new TodosDataAccess(connection);
        return new UpdateTodoUseCase(usersDataAccess, todosDataAccess);
    }

    public static SetTodoIsDoneUseCase getSetTodoIsDoneUseCase(DataSource connection) {
        final var usersDataAccess = new UsersDataAccess(connection);
        final var todosDataAccess = new TodosDataAccess(connection);
        return new SetTodoIsDoneUseCase(usersDataAccess, todosDataAccess);
    }

    public static SetTodoIsNotDoneUseCase getSetTodoIsNotDoneUseCase(DataSource connection) {
        final var usersDataAccess = new UsersDataAccess(connection);
        final var todosDataAccess = new TodosDataAccess(connection);
        return new SetTodoIsNotDoneUseCase(usersDataAccess, todosDataAccess);
    }

    public static ToggleTodoUseCase getToggleTodoUseCase(DataSource connection) {
        final var usersDataAccess = new UsersDataAccess(connection);
        final var todosDataAccess = new TodosDataAccess(connection);
        return new ToggleTodoUseCase(usersDataAccess, todosDataAccess);
    }

    public static DeleteTodoUseCase getDeleteTodoUseCase(DataSource connection) {
        final var usersDataAccess = new UsersDataAccess(connection);
        final var todosDataAccess = new TodosDataAccess(connection);
        return new DeleteTodoUseCase(usersDataAccess, todosDataAccess);
    }

    public static SimplifiedDeleteTodoUseCase getSimplifiedDeleteTodoUseCase(DataSource connection) {
        final var todosDataAccess = new TodosDataAccess(connection);
        return new SimplifiedDeleteTodoUseCase(todosDataAccess);
    }

}
