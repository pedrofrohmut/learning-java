package core.dataaccess;

import java.sql.SQLException;

import core.dtos.NewTodoFormDto;

public interface ITodosDataAccess {
    void create(NewTodoFormDto form, String authUserId) throws SQLException;
}
