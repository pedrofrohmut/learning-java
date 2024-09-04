package core.entities;

import java.sql.SQLException;

import core.dataaccess.ITodosDataAccess;
import core.dtos.NewTodoFormDto;
import core.exceptions.InvalidTodoException;

public class TodoEntity {
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
}
