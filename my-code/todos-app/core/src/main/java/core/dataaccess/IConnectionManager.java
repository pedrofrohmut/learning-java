package core.dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface IConnectionManager {
    Connection getOpenedConnection(String connectionString);
    void closeConnection(Connection connection);
}
