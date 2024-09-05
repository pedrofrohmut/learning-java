package core.dataaccess;

import java.sql.SQLException;
import java.util.Optional;

import core.dtos.NewTodoFormDto;
import core.dtos.TodoDbDto;

public interface ITodosDataAccess {
    void create(NewTodoFormDto form, String authUserId) throws SQLException;

    Optional<TodoDbDto> findById(String todoId) throws SQLException;
}
