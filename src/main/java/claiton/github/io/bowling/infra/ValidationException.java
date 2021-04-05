package claiton.github.io.bowling.infra;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
