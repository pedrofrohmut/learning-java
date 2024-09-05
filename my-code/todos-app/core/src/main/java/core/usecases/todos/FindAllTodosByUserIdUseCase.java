package core.usecases.todos;

import java.sql.SQLException;
import java.util.Collection;

import core.dataaccess.ITodosDataAccess;
import core.dataaccess.IUsersDataAccess;
import core.dtos.TodoDbDto;
import core.entities.TodoEntity;
import core.entities.UserEntity;
import core.exceptions.InvalidUserException;
import core.exceptions.ResourceOwnershipException;
import core.exceptions.UserNotFoundException;

public class FindAllTodosByUserIdUseCase {

    private final IUsersDataAccess usersDataAccess;
    private final ITodosDataAccess todosDataAccess;

    public FindAllTodosByUserIdUseCase(IUsersDataAccess usersDataAccess, ITodosDataAccess todosDataAccess) {
        this.usersDataAccess = usersDataAccess;
        this.todosDataAccess = todosDataAccess;
    }

    public Collection<TodoDbDto> execute(String userId, String authUserId)
            throws InvalidUserException, UserNotFoundException, SQLException, ResourceOwnershipException {
        UserEntity.validateId(userId);
        System.out.println("[Info] User id is valid");

        UserEntity.checkUserExistsById(userId, this.usersDataAccess);
        System.out.println("[Info] User with this id exists");

        UserEntity.checkIsTheSameUser(userId, authUserId);
        System.out.println("[Info] Is the same user");

        final var todos = TodoEntity.findTodosByUserId(userId, this.todosDataAccess);
        System.out.println("[Info] Todos retrieved by user id");

        return todos;
    }
}
