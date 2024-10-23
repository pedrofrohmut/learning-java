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
import core.dtos.UpdateTodoFormDto;
import core.utils.EnvUtils;
import dataaccess.ConnectionManager;
import jakarta.servlet.http.HttpServletRequest;
import utils.UseCasesFactory;
import webapi.exceptions.UnauthorizedRequestException;
import webapi.utils.ControllerUtils;

@RestController
@RequestMapping("api/todos")
public class TodosController {
    //
    //@PostMapping
    //public ResponseEntity<Object> createTodo(HttpServletRequest request, @RequestBody NewTodoFormDto form) {
    //    final var bearerToken = request.getHeader("Authorization");
    //    Connection connection = null;
    //    final var connectionManager = new ConnectionManager();
    //    try {
    //        final var authUserId = ControllerUtils.getUserIdFromToken(bearerToken);
    //        final var connectionString = EnvUtils.getConnectionString();
    //        connection = connectionManager.getOpenedConnection(connectionString);
    //        final var createTodoUseCase = UseCasesFactory.getCreateTodoUseCase(connection);
    //        final var response = TodosWebAdapter.create(createTodoUseCase, form, authUserId);
    //        return ResponseEntity.status(response.statusCode).body(response.body);
    //    } catch (UnauthorizedRequestException e) {
    //        return ControllerUtils.getUnauthorizedResponse();
    //    } finally {
    //        connectionManager.closeConnection(connection);
    //    }
    //}
    //
    //@GetMapping("{todoId}")
    //public ResponseEntity<Object> findOne(HttpServletRequest request, @PathVariable("todoId") String todoId) {
    //    final var bearerToken = request.getHeader("Authorization");
    //    Connection connection = null;
    //    final var connectionManager = new ConnectionManager();
    //    try {
    //        final var authUserId = ControllerUtils.getUserIdFromToken(bearerToken);
    //        final var connectionString = EnvUtils.getConnectionString();
    //        connection = connectionManager.getOpenedConnection(connectionString);
    //        final var findOneTodoUseCase = UseCasesFactory.getFindOneTodoUseCase(connection);
    //        final var response = TodosWebAdapter.findOne(findOneTodoUseCase, todoId, authUserId);
    //        return ResponseEntity.status(response.statusCode).body(response.body);
    //    } catch (UnauthorizedRequestException e) {
    //        return ControllerUtils.getUnauthorizedResponse();
    //    } finally {
    //        connectionManager.closeConnection(connection);
    //    }
    //}
    //
    //@GetMapping("user/{userId}")
    //public ResponseEntity<Object> findAllByUser(HttpServletRequest request, @PathVariable("userId") String userId) {
    //    final var bearerToken = request.getHeader("Authorization");
    //    Connection connection = null;
    //    final var connectionManager = new ConnectionManager();
    //    try {
    //        final var authUserId = ControllerUtils.getUserIdFromToken(bearerToken);
    //        final var connectionString = EnvUtils.getConnectionString();
    //        connection = connectionManager.getOpenedConnection(connectionString);
    //        final var findAllTodosByUserIdUseCase = UseCasesFactory.getFindAllTodosByUserIdUseCase(connection);
    //        final var response = TodosWebAdapter.findAllByUserId(findAllTodosByUserIdUseCase, userId, authUserId);
    //        return ResponseEntity.status(response.statusCode).body(response.body);
    //    } catch (UnauthorizedRequestException e) {
    //        return ControllerUtils.getUnauthorizedResponse();
    //    } finally {
    //        connectionManager.closeConnection(connection);
    //    }
    //}
    //
    //@PutMapping("{todoId}")
    //public ResponseEntity<Object> updateTodo(HttpServletRequest request, @PathVariable("todoId") String todoId,
    //        @RequestBody UpdateTodoFormDto form) {
    //    final var bearerToken = request.getHeader("Authorization");
    //    Connection connection = null;
    //    final var connectionManager = new ConnectionManager();
    //    try {
    //        final var authUserId = ControllerUtils.getUserIdFromToken(bearerToken);
    //        final var connectionString = EnvUtils.getConnectionString();
    //        connection = connectionManager.getOpenedConnection(connectionString);
    //        final var updateTodoUseCase = UseCasesFactory.getUpdateTodoUseCase(connection);
    //        final var response = TodosWebAdapter.update(updateTodoUseCase, todoId, form, authUserId);
    //        return ResponseEntity.status(response.statusCode).body(response.body);
    //    } catch (UnauthorizedRequestException e) {
    //        return ControllerUtils.getUnauthorizedResponse();
    //    } finally {
    //        connectionManager.closeConnection(connection);
    //    }
    //}
    //
    //@PutMapping("setdone/{todoId}")
    //public ResponseEntity<Object> setTodoIsDone(HttpServletRequest request, @PathVariable("todoId") String todoId) {
    //    final var bearerToken = request.getHeader("Authorization");
    //    Connection connection = null;
    //    final var connectionManager = new ConnectionManager();
    //    try {
    //        final var authUserId = ControllerUtils.getUserIdFromToken(bearerToken);
    //        final var connectionString = EnvUtils.getConnectionString();
    //        connection = connectionManager.getOpenedConnection(connectionString);
    //        final var setTodoIsDoneUseCase = UseCasesFactory.getSetTodoIsDoneUseCase(connection);
    //        final var response = TodosWebAdapter.setIsDone(setTodoIsDoneUseCase, todoId, authUserId);
    //        return ResponseEntity.status(response.statusCode).body(response.body);
    //    } catch (UnauthorizedRequestException e) {
    //        return ControllerUtils.getUnauthorizedResponse();
    //    } finally {
    //        connectionManager.closeConnection(connection);
    //    }
    //}
    //
    //@PutMapping("setnotdone/{todoId}")
    //public ResponseEntity<Object> setTodoIsNotDone(HttpServletRequest request, @PathVariable("todoId") String todoId) {
    //    final var bearerToken = request.getHeader("Authorization");
    //    Connection connection = null;
    //    final var connectionManager = new ConnectionManager();
    //    try {
    //        final var authUserId = ControllerUtils.getUserIdFromToken(bearerToken);
    //        final var connectionString = EnvUtils.getConnectionString();
    //        connection = connectionManager.getOpenedConnection(connectionString);
    //        final var setTodoIsNotDoneUseCase = UseCasesFactory.getSetTodoIsNotDoneUseCase(connection);
    //        final var response = TodosWebAdapter.setIsNotDone(setTodoIsNotDoneUseCase, todoId, authUserId);
    //        return ResponseEntity.status(response.statusCode).body(response.body);
    //    } catch (UnauthorizedRequestException e) {
    //        return ControllerUtils.getUnauthorizedResponse();
    //    } finally {
    //        connectionManager.closeConnection(connection);
    //    }
    //}
    //
    //@PutMapping("toggle/{todoId}")
    //public ResponseEntity<Object> toggleTodo(HttpServletRequest request, @PathVariable("todoId") String todoId) {
    //    final var bearerToken = request.getHeader("Authorization");
    //    Connection connection = null;
    //    final var connectionManager = new ConnectionManager();
    //    try {
    //        final var authUserId = ControllerUtils.getUserIdFromToken(bearerToken);
    //        final var connectionString = EnvUtils.getConnectionString();
    //        connection = connectionManager.getOpenedConnection(connectionString);
    //        final var toggleTodoUseCase = UseCasesFactory.getToggleTodoUseCase(connection);
    //        final var response = TodosWebAdapter.toggle(toggleTodoUseCase, todoId, authUserId);
    //        return ResponseEntity.status(response.statusCode).body(response.body);
    //    } catch (UnauthorizedRequestException e) {
    //        return ControllerUtils.getUnauthorizedResponse();
    //    } finally {
    //        connectionManager.closeConnection(connection);
    //    }
    //}
    //
    //@DeleteMapping("{todoId}")
    //public ResponseEntity<Object> deleteTodo(HttpServletRequest request, @PathVariable("todoId") String todoId) {
    //    final var bearerToken = request.getHeader("Authorization");
    //    Connection connection = null;
    //    final var connectionManager = new ConnectionManager();
    //    try {
    //        final var authUserId = ControllerUtils.getUserIdFromToken(bearerToken);
    //        final var connectionString = EnvUtils.getConnectionString();
    //        connection = connectionManager.getOpenedConnection(connectionString);
    //        final var deleteTodoUseCase = UseCasesFactory.getDeleteTodoUseCase(connection);
    //        final var response = TodosWebAdapter.delete(deleteTodoUseCase, todoId, authUserId);
    //        return ResponseEntity.status(response.statusCode).body(response.body);
    //    } catch (UnauthorizedRequestException e) {
    //        return ControllerUtils.getUnauthorizedResponse();
    //    } finally {
    //        connectionManager.closeConnection(connection);
    //    }
    //}
    //
    //@DeleteMapping("simplified/{todoId}")
    //public ResponseEntity<Object> simplifiedDeleteTodo(HttpServletRequest request, @PathVariable("todoId") String todoId) {
    //    final var bearerToken = request.getHeader("Authorization");
    //    Connection connection = null;
    //    final var connectionManager = new ConnectionManager();
    //    try {
    //        final var authUserId = ControllerUtils.getUserIdFromToken(bearerToken);
    //        final var connectionString = EnvUtils.getConnectionString();
    //        connection = connectionManager.getOpenedConnection(connectionString);
    //        final var simplifiedDeleteTodoUseCase = UseCasesFactory.getSimplifiedDeleteTodoUseCase(connection);
    //        final var response = TodosWebAdapter.simplifiedDelete(simplifiedDeleteTodoUseCase, todoId, authUserId);
    //        return ResponseEntity.status(response.statusCode).body(response.body);
    //    } catch (UnauthorizedRequestException e) {
    //        return ControllerUtils.getUnauthorizedResponse();
    //    } finally {
    //        connectionManager.closeConnection(connection);
    //    }
    //}
    //
}
