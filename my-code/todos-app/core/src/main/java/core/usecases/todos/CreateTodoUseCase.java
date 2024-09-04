package core.usecases.todos;

import java.sql.SQLException;

import core.dataaccess.ITodosDataAccess;
import core.dataaccess.IUsersDataAccess;
import core.dtos.NewTodoFormDto;
import core.entities.TodoEntity;
import core.entities.UserEntity;
import core.exceptions.InvalidTodoException;
import core.exceptions.InvalidUserException;
import core.exceptions.UserNotFoundException;

public class CreateTodoUseCase {

    private final IUsersDataAccess usersDataAccess;
    private final ITodosDataAccess todosDataAccess;

    public CreateTodoUseCase(IUsersDataAccess usersDataAccess, ITodosDataAccess todosDataAccess) {
        this.usersDataAccess = usersDataAccess;
        this.todosDataAccess = todosDataAccess;
    }

    public void execute(NewTodoFormDto form, String authUserId)
            throws InvalidUserException, InvalidTodoException, UserNotFoundException, SQLException {

        System.out.println("User id useCase: " + authUserId);

        UserEntity.validateId(authUserId);
        System.out.println("[Info] User id is valid");

        UserEntity.checkUserExistsById(authUserId, this.usersDataAccess);
        System.out.println("[Info] User with this id exists");

        TodoEntity.validateTodo(form);
        System.out.println("[Info] New todo is valid");

        TodoEntity.createTodo(form, authUserId, this.todosDataAccess);
        System.out.println("[Info] Todo created");
    }
}
