package by.training.catalog.bean;

public class UnknownTypeException extends Exception {
    public UnknownTypeException() {
    }

    public UnknownTypeException(final String message) {
        super(message);
    }

    public UnknownTypeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnknownTypeException(final Throwable cause) {
        super(cause);
    }
}
