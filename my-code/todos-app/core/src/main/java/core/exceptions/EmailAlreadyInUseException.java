package core.exceptions;

public class EmailAlreadyInUseException extends Exception {
    public EmailAlreadyInUseException() {
        super("E-mail address already in use");
    }
}
