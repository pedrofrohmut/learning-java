package my.goals.application;

import java.sql.Connection;

public interface IConnectionManager {
    Connection getConnection();
    void closeConnection(Connection connection);
}
