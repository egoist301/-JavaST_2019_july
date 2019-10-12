package by.training.catalog.dao;

/**
 * Dao exception.
 */
public class PersistentException extends Exception {
    /**
     * Default constructor.
     */
    public PersistentException() {
    }

    /**
     * Constructor with message.
     *
     * @param message error message.
     */
    public PersistentException(final String message) {
        super(message);
    }

    /**
     * Constructor with all parameters.
     *
     * @param message error message.
     * @param cause   exception.
     */
    public PersistentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     *
     * @param cause exception.
     */
    public PersistentException(final Throwable cause) {
        super(cause);
    }
}
