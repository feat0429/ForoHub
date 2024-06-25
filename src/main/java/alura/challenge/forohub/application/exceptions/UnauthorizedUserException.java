package alura.challenge.forohub.application.exceptions;

public class UnauthorizedUserException extends Exception {
    public UnauthorizedUserException(String message) {
        super(message);
    }
}
