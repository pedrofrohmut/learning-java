package core.adapters.web;

import core.dtos.AdaptedWebResponse;
import core.dtos.NewTodoFormDto;
import core.exceptions.InvalidTodoException;
import core.exceptions.InvalidUserException;
import core.exceptions.UserNotFoundException;
import core.usecases.todos.CreateTodoUseCase;

public class TodosWebAdapter {
    public static AdaptedWebResponse createTodo(CreateTodoUseCase useCase, NewTodoFormDto form, String authUserId) {
        try {
            useCase.execute(form, authUserId);
            return AdaptedWebResponse.of(201);
        } catch (InvalidUserException | InvalidTodoException | UserNotFoundException e) {
            return AdaptedWebResponse.of(400, e.getMessage());
        } catch (Exception e) {
            return AdaptedWebResponse.of(500, e.getMessage());
        }
    }
}
