package by.training.catalog.dao;

/**
 * Dao exception. Catch SQL exception.
 */
public class PersistenceException extends Exception {
    /**
     * Default constructor.
     */
    public PersistenceException() {
    }

    /**
     * Constructor with message.
     *
     * @param message error message.
     */
    public PersistenceException(final String message) {
        super(message);
    }

    /**
     * Constructor with all parameters.
     *
     * @param message error message.
     * @param cause   exception.
     */
    public PersistenceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     *
     * @param cause exception.
     */
    public PersistenceException(final Throwable cause) {
        super(cause);
    }
}
