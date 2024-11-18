package my.goals.infra.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import my.goals.application.dataaccess.IGoalsDataAccess;
import my.goals.entities.Goal;

@Component("PostgresGoalsDataAccess")
public class PostgresGoalsDataAccess implements IGoalsDataAccess {

    private Connection connection;

    public PostgresGoalsDataAccess() {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Goal goal) {
        final var sql = "INSERT INTO goals (id, description, is_done) VALUES (?, ?, ?)";
        try (final var stm = this.connection.prepareStatement(sql)) {
            stm.setString(1, goal.getId());
            stm.setString(2, goal.getDescription());
            stm.setBoolean(3, goal.getIsDone());
            return stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error to create a goal. With message: " + e.getMessage());
        }
    }

    @Override
    public List<Goal> findAll() {
        final var sql = "SELECT id, description, is_done FROM goals";
        try (final var stm = this.connection.prepareStatement(sql)) {
            try (final var rs = stm.executeQuery()) {
                final var goals = new ArrayList<Goal>();
                while (rs.next()) {
                    final var goal = new Goal(rs.getString("id"), rs.getString("description"),
                            rs.getBoolean("is_done"));
                    goals.add(goal);
                }
                return goals;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error to find all goals. With message: " + e.getMessage());
        }
    }

    @Override
    public int delete(String id) {
        final var sql = "DELETE FROM goals WHERE id = ?";
        try (final var stm = this.connection.prepareStatement(sql)) {
            stm.setString(1, id);
            return stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error to delete goal. With message: " + e.getMessage());
        }
    }

    @Override
    public Optional<Goal> findById(String id) {
        final var sql = "SELECT description, is_done FROM goals WHERE id = ?";
        try (final var stm = this.connection.prepareStatement(sql)) {
            stm.setString(1, id);
            try (final var rs = stm.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                final var goal = new Goal(id, rs.getString("description"), rs.getBoolean("is_done"));
                return Optional.of(goal);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error to find goal by id. With message: " + e.getMessage());
        }
    }

}
