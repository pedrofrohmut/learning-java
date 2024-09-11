package core.dataaccess;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import core.dtos.NewTodoFormDto;
import core.dtos.TodoDbDto;
import core.dtos.UpdateTodoFormDto;

public interface ITodosDataAccess {
    void create(NewTodoFormDto form, String authUserId) throws SQLException;

    Optional<TodoDbDto> findById(String todoId) throws SQLException;

    Optional<Collection<TodoDbDto>> findByUserId(String userId) throws SQLException;

    void update(String todoId, UpdateTodoFormDto form) throws SQLException;

    void setIsDone(String todoId) throws SQLException;

    void setIsNotDone(String todoId) throws SQLException;

    void delete(String todoId) throws SQLException;

    int deleteOfUser(String todoId, String userId) throws SQLException;
}
