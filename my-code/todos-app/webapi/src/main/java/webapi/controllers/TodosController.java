package webapi.controllers;

import java.sql.Connection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.adapters.web.TodosWebAdapter;
import core.dtos.NewTodoFormDto;
import core.utils.EnvUtils;
import dataaccess.ConnectionManager;
import jakarta.servlet.http.HttpServletRequest;
import utils.UseCasesFactory;
import webapi.exceptions.UnauthorizedRequestException;
import webapi.utils.ControllerUtils;

@RestController
@RequestMapping("api/todos")
public class TodosController {

    @PostMapping
    public ResponseEntity<Object> createTodo(HttpServletRequest request, @RequestBody NewTodoFormDto form) {
        final var bearerToken = request.getHeader("Authorization");
        Connection connection = null;
        final var connectionManager = new ConnectionManager();
        try {
            final var authUserId = ControllerUtils.getUserIdFromToken(bearerToken);
            final var connectionString = EnvUtils.getConnectionString();
            connection = connectionManager.getOpenedConnection(connectionString);
            final var createTodoUseCase = UseCasesFactory.getCreateTodoUseCase(connection);
            final var response = TodosWebAdapter.createTodo(createTodoUseCase, form, authUserId);
            return ResponseEntity.status(response.statusCode).body(response.body);
        } catch (UnauthorizedRequestException e) {
            return ControllerUtils.getUnauthorizedResponse();
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

    @GetMapping("{todoId}")
    public ResponseEntity<Object> findOne(HttpServletRequest request, @PathVariable("todoId") String todoId) {
        final var bearerToken = request.getHeader("Authorization");
        Connection connection = null;
        final var connectionManager = new ConnectionManager();
        try {
            final var authUserId = ControllerUtils.getUserIdFromToken(bearerToken);
            final var connectionString = EnvUtils.getConnectionString();
            connection = connectionManager.getOpenedConnection(connectionString);
            final var findOneTodoUseCase = UseCasesFactory.getFindOneTodoUseCase(connection);
            final var response = TodosWebAdapter.findOne(findOneTodoUseCase, todoId, authUserId);
            return ResponseEntity.status(response.statusCode).body(response.body);
        } catch (UnauthorizedRequestException e) {
            return ControllerUtils.getUnauthorizedResponse();
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

    @GetMapping("user/{userId}")
    public String findAllByUser(@PathVariable("userId") String userId) {
        return "Find all by user id";
    }

    @PutMapping("{todoId}")
    public String updateTodo(@PathVariable String todoId) {
        return "Update todo";
    }

    @PutMapping("toggle/{todoId}")
    public String toggleTodo(@PathVariable String todoId) {
        return "Toggle todo";
    }

    @DeleteMapping("{todoId}")
    public String deleteTodo(@PathVariable String todoId) {
        return "Delete todo";
    }

}
