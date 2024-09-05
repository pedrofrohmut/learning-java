package core.usecases.todos;

import java.sql.SQLException;

import core.dataaccess.ITodosDataAccess;
import core.dataaccess.IUsersDataAccess;
import core.dtos.TodoDbDto;
import core.entities.TodoEntity;
import core.entities.UserEntity;
import core.exceptions.InvalidTodoException;
import core.exceptions.InvalidUserException;
import core.exceptions.ResourceOwnershipException;
import core.exceptions.TodoNotFoundException;
import core.exceptions.UserNotFoundException;

public class FindOneTodoUseCase {

    private final IUsersDataAccess usersDataAccess;
    private final ITodosDataAccess todosDataAccess;

    public FindOneTodoUseCase(IUsersDataAccess usersDataAccess, ITodosDataAccess todosDataAccess) {
        this.usersDataAccess = usersDataAccess;
        this.todosDataAccess = todosDataAccess;
    }

    public TodoDbDto execute(String todoId, String authUserId) throws InvalidUserException, InvalidTodoException,
            TodoNotFoundException, SQLException, UserNotFoundException, ResourceOwnershipException {
        TodoEntity.validateId(todoId);
        System.out.println("[Info] Todo id is valid");

        final var todo = TodoEntity.findTodoById(todoId, this.todosDataAccess);
        System.out.println("[Info] Todo found by id");

        UserEntity.validateId(authUserId);
        System.out.println("[Info] User id is valid");

        UserEntity.checkUserExistsById(authUserId, this.usersDataAccess);
        System.out.println("[Info] User found by id");

        TodoEntity.checkForTodoOwnership(todo, authUserId);
        System.out.println("[Info] Todo belongs to this user");

        return todo;
    }
}
