package dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import core.dataaccess.ITodosDataAccess;
import core.dtos.NewTodoFormDto;

public class TodosDataAccess implements ITodosDataAccess {

    private final Connection connection;

    public TodosDataAccess(Connection connection) {
        this.connection = connection;
    }

	@Override
	public void create(NewTodoFormDto form, String authUserId) throws SQLException {
        var sql = "INSERT INTO todos (name, description, user_id) VALUES (?, ?, ?)";
        try (var stm = this.connection.prepareStatement(sql)) {
            stm.setString(1, form.name);
            stm.setString(2, form.description);
            stm.setObject(3, UUID.fromString(authUserId));
            stm.execute();
        }
	}
}
