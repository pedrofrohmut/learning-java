package core.adapters.web;

import core.dtos.AdaptedWebResponse;
import core.dtos.NewTodoFormDto;
import core.exceptions.InvalidTodoException;
import core.exceptions.InvalidUserException;
import core.exceptions.ResourceOwnershipException;
import core.exceptions.TodoNotFoundException;
import core.exceptions.UserNotFoundException;
import core.usecases.todos.CreateTodoUseCase;
import core.usecases.todos.FindOneTodoUseCase;

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

    public static AdaptedWebResponse findOne(FindOneTodoUseCase useCase, String todoId, String authUserId) {
        try {
            final var todo = useCase.execute(todoId, authUserId);
            return AdaptedWebResponse.of(200, todo);
        } catch (InvalidTodoException | InvalidUserException | UserNotFoundException e) {
            return AdaptedWebResponse.of(400, e.getMessage());
        } catch (TodoNotFoundException e) {
            return AdaptedWebResponse.of(404, e.getMessage());
        } catch (ResourceOwnershipException e) {
            return AdaptedWebResponse.of(403, e.getMessage());
        } catch (Exception e) {
            return AdaptedWebResponse.of(500, e.getMessage());
        }
    }
}
