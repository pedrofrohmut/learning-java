package dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import core.dataaccess.ITodosDataAccess;
import core.dtos.NewTodoFormDto;
import core.dtos.TodoDbDto;
import core.dtos.UpdateTodoFormDto;

public class TodosDataAccess implements ITodosDataAccess {

    private final Connection connection;

    public TodosDataAccess(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(NewTodoFormDto form, String authUserId) throws SQLException {
        final var sql = "INSERT INTO todos (name, description, user_id) VALUES (?, ?, ?)";
        try (final var stm = this.connection.prepareStatement(sql)) {
            stm.setString(1, form.name);
            stm.setString(2, form.description);
            stm.setObject(3, UUID.fromString(authUserId));
            stm.execute();
        }
    }

    @Override
    public Optional<TodoDbDto> findById(String todoId) throws SQLException {
        final var sql = "SELECT id, name, description, user_id FROM todos WHERE id = ?";
        try (final var stm = this.connection.prepareStatement(sql)) {
            stm.setObject(1, UUID.fromString(todoId));
            try (final var rs = stm.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                final var todo = new TodoDbDto();
                todo.id = rs.getString("id");
                todo.name = rs.getString("name");
                todo.description = rs.getString("description");
                todo.userId = rs.getString("user_id");
                return Optional.of(todo);
            }
        }
    }

	@Override
	public Optional<Collection<TodoDbDto>> findByUserId(String userId) throws SQLException {
        final var sql = "SELECT id, name, description, user_id FROM todos WHERE user_id = ?";
        try (final var stm = this.connection.prepareStatement(sql)) {
            stm.setObject(1, UUID.fromString(userId));
            try (final var rs = stm.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                final var todos = new ArrayList<TodoDbDto>();
                // Do-While to not skip the first position of the if check above
                do {
                    final var todo = new TodoDbDto();
                    todo.id = rs.getString("id");
                    todo.name = rs.getString("name");
                    todo.description = rs.getString("description");
                    todo.userId = rs.getString("user_id");
                    todos.add(todo);
                } while(rs.next());
                return Optional.of(todos);
            }
        }
	}

	@Override
	public void update(String todoId, UpdateTodoFormDto form) throws SQLException {
        final var sql = "UPDATE todos SET name = ?, description = ? WHERE id = ?";
        try (final var stm = this.connection.prepareStatement(sql)) {
            stm.setString(1, form.name);
            stm.setString(2, form.description);
            stm.setObject(3, UUID.fromString(todoId));
            stm.execute();
        }
    }

	@Override
	public void setIsDone(String todoId) throws SQLException {
        final var sql = "UPDATE todos SET is_done = true WHERE id = ?";
        try (final var stm = this.connection.prepareStatement(sql)) {
            stm.setObject(1, UUID.fromString(todoId));
            stm.execute();
        }
	}

	@Override
	public void setIsNotDone(String todoId) throws SQLException {
        final var sql = "UPDATE todos SET is_done = false WHERE id = ?";
        try (final var stm = this.connection.prepareStatement(sql)) {
            stm.setObject(1, UUID.fromString(todoId));
            stm.execute();
        }
	}

}
