package core.usecases.todos;

import java.sql.SQLException;

import core.dataaccess.ITodosDataAccess;
import core.dataaccess.IUsersDataAccess;
import core.dtos.UpdateTodoFormDto;
import core.entities.TodoEntity;
import core.entities.UserEntity;
import core.exceptions.InvalidTodoException;
import core.exceptions.InvalidUserException;
import core.exceptions.ResourceOwnershipException;
import core.exceptions.TodoNotFoundException;
import core.exceptions.UserNotFoundException;

public class UpdateTodoUseCase {

    private final IUsersDataAccess usersDataAccess;
    private final ITodosDataAccess todosDataAccess;

    public UpdateTodoUseCase(IUsersDataAccess usersDataAccess, ITodosDataAccess todosDataAccess) {
        this.usersDataAccess = usersDataAccess;
        this.todosDataAccess = todosDataAccess;
    }

    public void execute(String todoId, UpdateTodoFormDto form, String authUserId)
            throws InvalidTodoException, InvalidUserException, UserNotFoundException, SQLException,
            TodoNotFoundException, ResourceOwnershipException {
        TodoEntity.validateId(todoId);
        System.out.println("[Info] Todo id is valid");

        TodoEntity.validateTodo(form);
        System.out.println("[Info] Update todo form is valid");

        UserEntity.validateId(authUserId);
        System.out.println("[Info] User id is valid");

        UserEntity.checkUserExistsById(authUserId, this.usersDataAccess);
        System.out.println("[Info] User with this id exists");

        final var todo = TodoEntity.findTodoById(todoId, this.todosDataAccess);
        System.out.println("[Info] Todo found by id");

        TodoEntity.checkForTodoOwnership(todo, authUserId);
        System.out.println("[Info] This todo belongs to this user");

        TodoEntity.updateTodo(todoId, form, this.todosDataAccess);
        System.out.println("[Info] Todo updated");
    }
}
