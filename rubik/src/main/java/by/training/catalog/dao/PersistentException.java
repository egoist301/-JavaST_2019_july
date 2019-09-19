package by.training.catalog.dao;

public class PersistentException extends Exception {
    public PersistentException() {
    }

    public PersistentException(final String message) {
        super(message);
    }

    public PersistentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PersistentException(final Throwable cause) {
        super(cause);
    }
}
