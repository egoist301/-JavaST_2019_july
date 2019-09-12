package by.training.catalog.dao;

public class PersistenceException extends Exception {
    public PersistenceException() {
    }

    public PersistenceException(final String message) {
        super(message);
    }

    public PersistenceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PersistenceException(final Throwable cause) {
        super(cause);
    }
}
