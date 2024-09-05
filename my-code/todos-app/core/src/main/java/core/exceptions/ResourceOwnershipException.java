package core.exceptions;

public class ResourceOwnershipException extends Exception {
    public ResourceOwnershipException(String message) {
        super(message);
    }

    public ResourceOwnershipException() {
        super("This resource does not belong to the user requiring it.");
    }
}
