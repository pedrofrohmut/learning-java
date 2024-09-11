package core.adapters.web;

import core.dtos.AdaptedWebResponse;
import core.dtos.NewTodoFormDto;
import core.dtos.UpdateTodoFormDto;
import core.exceptions.InvalidTodoException;
import core.exceptions.InvalidUserException;
import core.exceptions.ResourceOwnershipException;
import core.exceptions.TodoNotFoundException;
import core.exceptions.UserNotFoundException;
import core.usecases.todos.CreateTodoUseCase;
import core.usecases.todos.DeleteTodoUseCase;
import core.usecases.todos.FindAllTodosByUserIdUseCase;
import core.usecases.todos.FindOneTodoUseCase;
import core.usecases.todos.SetTodoIsDoneUseCase;
import core.usecases.todos.SetTodoIsNotDoneUseCase;
import core.usecases.todos.SimplifiedDeleteTodoUseCase;
import core.usecases.todos.ToggleTodoUseCase;
import core.usecases.todos.UpdateTodoUseCase;

public class TodosWebAdapter {
    public static AdaptedWebResponse create(CreateTodoUseCase useCase, NewTodoFormDto form, String authUserId) {
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

    public static AdaptedWebResponse findAllByUserId(FindAllTodosByUserIdUseCase useCase, String userId,
            String authUserId) {
        try {
            final var todos = useCase.execute(userId, authUserId);
            return AdaptedWebResponse.of(200, todos);
        } catch (InvalidUserException | UserNotFoundException e) {
            return AdaptedWebResponse.of(400, e.getMessage());
        } catch (ResourceOwnershipException e) {
            return AdaptedWebResponse.of(403, e.getMessage());
        } catch (Exception e) {
            return AdaptedWebResponse.of(500, e.getMessage());
        }
    }

    public static AdaptedWebResponse update(UpdateTodoUseCase useCase, String userId, UpdateTodoFormDto form,
            String authUserId) {
        try {
            useCase.execute(userId, form, authUserId);
            return AdaptedWebResponse.of(204);
        } catch (InvalidUserException | UserNotFoundException | InvalidTodoException e) {
            return AdaptedWebResponse.of(400, e.getMessage());
        } catch (TodoNotFoundException e) {
            return AdaptedWebResponse.of(404, e.getMessage());
        } catch (ResourceOwnershipException e) {
            return AdaptedWebResponse.of(403, e.getMessage());
        } catch (Exception e) {
            return AdaptedWebResponse.of(500, e.getMessage());
        }
    }

    public static AdaptedWebResponse setIsDone(SetTodoIsDoneUseCase useCase, String userId, String authUserId) {
        try {
            useCase.execute(userId, authUserId);
            return AdaptedWebResponse.of(204);
        } catch (InvalidUserException | UserNotFoundException | InvalidTodoException e) {
            return AdaptedWebResponse.of(400, e.getMessage());
        } catch (TodoNotFoundException e) {
            return AdaptedWebResponse.of(404, e.getMessage());
        } catch (ResourceOwnershipException e) {
            return AdaptedWebResponse.of(403, e.getMessage());
        } catch (Exception e) {
            return AdaptedWebResponse.of(500, e.getMessage());
        }
    }

    public static AdaptedWebResponse setIsNotDone(SetTodoIsNotDoneUseCase useCase, String userId, String authUserId) {
        try {
            useCase.execute(userId, authUserId);
            return AdaptedWebResponse.of(204);
        } catch (InvalidUserException | UserNotFoundException | InvalidTodoException e) {
            return AdaptedWebResponse.of(400, e.getMessage());
        } catch (TodoNotFoundException e) {
            return AdaptedWebResponse.of(404, e.getMessage());
        } catch (ResourceOwnershipException e) {
            return AdaptedWebResponse.of(403, e.getMessage());
        } catch (Exception e) {
            return AdaptedWebResponse.of(500, e.getMessage());
        }
    }

    public static AdaptedWebResponse toggle(ToggleTodoUseCase useCase, String userId, String authUserId) {
        try {
            useCase.execute(userId, authUserId);
            return AdaptedWebResponse.of(204);
        } catch (InvalidUserException | UserNotFoundException | InvalidTodoException e) {
            return AdaptedWebResponse.of(400, e.getMessage());
        } catch (TodoNotFoundException e) {
            return AdaptedWebResponse.of(404, e.getMessage());
        } catch (ResourceOwnershipException e) {
            return AdaptedWebResponse.of(403, e.getMessage());
        } catch (Exception e) {
            return AdaptedWebResponse.of(500, e.getMessage());
        }
    }

    public static AdaptedWebResponse delete(DeleteTodoUseCase useCase, String userId, String authUserId) {
        try {
            useCase.execute(userId, authUserId);
            return AdaptedWebResponse.of(204);
        } catch (InvalidUserException | UserNotFoundException | InvalidTodoException e) {
            return AdaptedWebResponse.of(400, e.getMessage());
        } catch (TodoNotFoundException e) {
            return AdaptedWebResponse.of(404, e.getMessage());
        } catch (ResourceOwnershipException e) {
            return AdaptedWebResponse.of(403, e.getMessage());
        } catch (Exception e) {
            return AdaptedWebResponse.of(500, e.getMessage());
        }
    }

    public static AdaptedWebResponse simplifiedDelete(SimplifiedDeleteTodoUseCase useCase, String userId, String authUserId) {
        try {
            useCase.execute(userId, authUserId);
            return AdaptedWebResponse.of(204);
        } catch (InvalidUserException | InvalidTodoException e) {
            return AdaptedWebResponse.of(400, e.getMessage());
        } catch (TodoNotFoundException e) {
            return AdaptedWebResponse.of(404, e.getMessage());
        } catch (Exception e) {
            return AdaptedWebResponse.of(500, e.getMessage());
        }
    }

}
