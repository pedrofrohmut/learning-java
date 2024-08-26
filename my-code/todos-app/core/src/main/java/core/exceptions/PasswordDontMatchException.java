package core.exceptions;

public class PasswordDontMatchException extends Exception {
    public PasswordDontMatchException() {
        super("Password and password hash dont match each other");
    }
}
