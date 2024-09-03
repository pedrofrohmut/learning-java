package webapi.exceptions;

public class UnauthorizedRequestException extends Exception {
    public UnauthorizedRequestException() {
        super("The request has no authorization or it is invalid.");
    }
}
