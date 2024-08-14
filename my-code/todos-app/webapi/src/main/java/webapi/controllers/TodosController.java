package webapi.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/todos")
public class TodosController {

    @PostMapping
    public String createTodo() {
        return "Create todo";
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
