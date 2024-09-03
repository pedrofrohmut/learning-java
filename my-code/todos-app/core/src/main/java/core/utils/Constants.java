package core.utils;

public class Constants {
    public static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static final String phoneRegex = "\\d{3}-\\d{3}-\\d{4}";

    private static final long second = 1000;
    private static final long minute = 60 * second;
    private static final long hour = 60 * minute;
    public static final long jwtDuration = 1 * hour;
}
