package core.utils;

public class EnvUtils {
    public static String getConnectionString() {
        //return System.getenv("JDBC_CONNECTION");
        return "jdbc:postgresql://localhost:5100/postgres?user=postgres&password=password";
    }

    public static String getJwtSecretKey() {
        //return System.getenv("JWT_SECRET");
        return "GJmNARPHxxVn510S19vcuoty8zoDzfRgt0WOL5PFKNs=";
    }
}
