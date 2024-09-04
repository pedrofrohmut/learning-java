package core.dataaccess;

import java.sql.Connection;

public interface IConnectionManager {
    Connection getOpenedConnection(String connectionString);

    void closeConnection(Connection connection);
}
