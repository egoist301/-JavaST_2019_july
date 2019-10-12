package by.training.catalog.dao.pool;

/**
 * Connection pool exception. Runtime exception.
 */
public class ConnectionPoolException extends RuntimeException {
    /**
     * Default constructor.
     */
    public ConnectionPoolException() {
    }

    /**
     * Constructor with error message.
     *
     * @param message error message.
     */
    public ConnectionPoolException(final String message) {
        super(message);
    }

    /**
     * Constructor with all parameters.
     *
     * @param message error message.
     * @param cause   exception.
     */
    public ConnectionPoolException(final String message,
                                   final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     *
     * @param cause exception.
     */
    public ConnectionPoolException(final Throwable cause) {
        super(cause);
    }
}
