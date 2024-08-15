package utils;

public class EnvUtils {
    public static String getConnectionString() {
        return "jdbc:postgresql://localhost:5100/postgres?user=postgres&password=password";
    }
}
