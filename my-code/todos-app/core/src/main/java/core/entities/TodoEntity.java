package core.entities;

import java.sql.SQLException;
import java.util.UUID;

import core.dataaccess.ITodosDataAccess;
import core.dtos.NewTodoFormDto;
import core.dtos.TodoDbDto;
import core.exceptions.InvalidTodoException;
import core.exceptions.ResourceOwnershipException;
import core.exceptions.TodoNotFoundException;

public class TodoEntity {
    public static void validateId(String id) throws InvalidTodoException {
        if (id == null || id.isBlank()) {
            throw new InvalidTodoException("TodoId not provided. TodoId is required and cannot be empty.");
        }
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidTodoException("TodoId not valid. TodoId does not have a valid format.");
        }
    }

    public static void validateName(String name) throws InvalidTodoException {
        if (name == null || name.isBlank()) {
            throw new InvalidTodoException("Name not provided. Name is required and cannot be empty.");
        }
        if (name.length() < 3) {
            throw new InvalidTodoException("Name is too short. Name must be at least 3 characters long.");
        }
        if (name.length() > 256) {
            throw new InvalidTodoException("Name is too long. Name must be less than 255 characters long.");
        }
    }

    public static void validateDescription(String description) throws InvalidTodoException {
        if (description == null || description.isBlank()) {
            throw new InvalidTodoException("Description not provided. Description is required and cannot be empty.");
        }
        if (description.length() < 3) {
            throw new InvalidTodoException("Description is too short. Description must be at least 3 characters long.");
        }
        if (description.length() > 512) {
            throw new InvalidTodoException(
                    "Description is too long. Description must be less than 255 characters long.");
        }
    }

    public static void validateTodo(NewTodoFormDto form) throws InvalidTodoException {
        validateName(form.name);
        validateDescription(form.description);
    }

    public static void createTodo(NewTodoFormDto form, String authUserId, ITodosDataAccess todosDataAccess)
            throws SQLException {
        todosDataAccess.create(form, authUserId);
    }

    public static TodoDbDto findTodoById(String todoId, ITodosDataAccess todosDataAccess)
            throws SQLException, TodoNotFoundException {
        final var todo = todosDataAccess.findById(todoId);
        if (!todo.isPresent()) {
            throw new TodoNotFoundException("Todo not found by id");
        }
        return todo.get();
    }

    public static void checkForTodoOwnership(TodoDbDto todo, String userId) throws ResourceOwnershipException {
        if (!todo.userId.equals(userId)) {
            throw new ResourceOwnershipException("This todo does not belong to this user");
        }
    }
}