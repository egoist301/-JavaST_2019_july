package by.training.catalog.dao.pool;

public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException() {
    }

    public ConnectionPoolException(final String message) {
        super(message);
    }

    public ConnectionPoolException(final String message,
                                   final Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(final Throwable cause) {
        super(cause);
    }
}
