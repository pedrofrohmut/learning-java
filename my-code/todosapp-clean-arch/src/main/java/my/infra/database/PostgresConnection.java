package my.infra.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresConnection {

    public static Connection getConnection() {
    	try {
    		final var props = new Properties();
    		props.setProperty("user", "postgres");
    		props.setProperty("password", "password");
    		return DriverManager.getConnection("jdbc:postgresql://localhost:5105/", props);
    	} catch (SQLException e) {
    		throw new RuntimeException("Could not connect to postgres database. With message: " + e.getMessage());
    	}
    }
    
}
