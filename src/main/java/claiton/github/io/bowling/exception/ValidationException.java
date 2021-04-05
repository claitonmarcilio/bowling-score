package claiton.github.io.bowling.exception;

/**
 * Thrown when an application rule is violated
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
