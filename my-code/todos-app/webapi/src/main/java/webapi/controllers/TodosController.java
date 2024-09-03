package webapi.controllers;

import java.sql.Connection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dataaccess.ConnectionManager;
import jakarta.servlet.http.HttpServletRequest;
import webapi.exceptions.UnauthorizedRequestException;
import webapi.utils.ControllerUtils;

@RestController
@RequestMapping("api/todos")
public class TodosController {

    @PostMapping
    public ResponseEntity<Object> createTodo(HttpServletRequest request) {
        var bearerToken = request.getHeader("Authorization");

        Connection connection = null;
        var connectionManager = new ConnectionManager();
        try {
            final var authUserId = ControllerUtils.getUserIdFromToken(bearerToken);

            return ResponseEntity.status(201).body("Todo Created");
        } catch (UnauthorizedRequestException e) {
            return ControllerUtils.getUnauthorizedResponse();
        } finally {
            connectionManager.closeConnection(connection);
        }
    }

    @GetMapping("{todoId}")
    public String findOne(@PathVariable String todoId) {
        return "Find one";
    }

    @GetMapping("{userId}")
    public String findAllByUser(@PathVariable String userId) {
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
