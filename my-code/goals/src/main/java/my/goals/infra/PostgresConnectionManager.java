package my.goals.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.stereotype.Component;

import my.goals.application.IConnectionManager;

@Component("PostgresConnectionManager")
public class PostgresConnectionManager implements IConnectionManager {

    private final String url;
    private final Properties props;

    public PostgresConnectionManager() {
	this.url = "jdbc:postgresql://localhost:5103/";
	final var props = new Properties();
	props.setProperty("user", "postgres");
	props.setProperty("password", "123456");
	this.props = props;
    }

    @Override
    public Connection getConnection() {
	try {
	    final var connection = DriverManager.getConnection(this.url, this.props);
	    if (connection.isValid(300)) {
		System.out.println("Connected to the database");
	    }
	    return connection;
	} catch (SQLException e) {
	    throw new RuntimeException("ERROR: Could not connect to the database. With message: " + e.getMessage());
	}
    }

    @Override
    public void closeConnection(Connection connection) {
	try {
	    if (connection != null) {
		connection.close();
	    }
	} catch (SQLException e) {
	    throw new RuntimeException("ERROR: Could not close the postgres database connection properly. With message: "
				       + e.getMessage());
	}
    }

}
