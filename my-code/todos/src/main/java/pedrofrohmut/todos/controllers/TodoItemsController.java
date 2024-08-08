package pedrofrohmut.todos.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/todo_items")
public class TodoItemsController {

    @PostMapping
    public String createTodoItem() {
        return "Create todo item";
    }

    @GetMapping("{todoItemId}")
    public String findOne(@PathVariable String todoItemId) {
        return "Find one";
    }

    @GetMapping("{todoId}")
    public String findAllByTodoId(@PathVariable String todoId) {
        return "Find by todo id";
    }

    @PutMapping("{todoItemId}")
    public String updateTodoItem(@PathVariable String todoItemId) {
        return "Update todo item";
    }

    @PutMapping("toggle/{todoItemId}")
    public String toggleTodoItem(@PathVariable String todoItemId) {
        return "Toggle todo item";
    }

    @DeleteMapping("{todoItemId}")
    public String deleteTodoItem(@PathVariable String todoItemId) {
        return "Delete todo item";
    }

}
