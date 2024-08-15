package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

import core.dataaccess.IConnectionManager;

public class ConnectionManager implements IConnectionManager {

    public Connection getOpenedConnection(String connectionString) {
        try {
            var connection = DriverManager.getConnection(connectionString);
            if (! connection.isValid(1000)) {
                throw new RuntimeException("Error trying to get an opened connection. The connection is not valid");
            }
            System.out.println("Connection is opened");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Error to open the database connection: " + e.getMessage());
        }
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error to close the connection to the database");
        }
    }
}
