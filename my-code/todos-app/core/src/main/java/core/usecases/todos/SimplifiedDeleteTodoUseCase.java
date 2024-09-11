package core.usecases.todos;

import java.sql.SQLException;

import core.dataaccess.ITodosDataAccess;
import core.entities.TodoEntity;
import core.entities.UserEntity;
import core.exceptions.InvalidTodoException;
import core.exceptions.InvalidUserException;
import core.exceptions.TodoNotFoundException;

public class SimplifiedDeleteTodoUseCase {

    private final ITodosDataAccess todosDataAccess;

    public SimplifiedDeleteTodoUseCase(ITodosDataAccess todosDataAccess) {
        this.todosDataAccess = todosDataAccess;
    }

    /*
         This one works just like the other but the messages are not very clear for
       the user of the API. When one user tries to delete some other user todo they
       get just that the todo was not found. Or when the user id is wrong.
         This version is cheaper in resources having only one reach to the Database
       but it loses on user expirence. Both versions are valid. This could be called
       the performance version.
         A third version could be made where you have the same number of check on
       the full version but when you check for user exists and todo exists you
       check for the in a simplified and updated Cache. Like just a list of like
       "existingUsersIds" and "existingTodosIds" making that version to reach to
       the database only once but with the same UX as the full version.
    */
    public void execute(String todoId, String authUserId)
            throws InvalidTodoException, InvalidUserException, SQLException, TodoNotFoundException {

        TodoEntity.validateId(todoId);
        System.out.println("[Info] Todo id is valid");

        UserEntity.validateId(authUserId);
        System.out.println("[Info] User id is valid");

        TodoEntity.deleteTodoOfUser(todoId, authUserId, this.todosDataAccess);
        System.out.println("[Info] Todo deleted");
    }
}
