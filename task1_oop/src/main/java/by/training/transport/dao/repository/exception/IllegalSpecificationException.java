package by.training.transport.dao.repository.exception;

public class IllegalSpecificationException extends Exception {
    /**
     * Default constructor.
     */
    public IllegalSpecificationException() {
    }

    /**
     * @param message error message.
     */
    public IllegalSpecificationException(final String message) {
        super(message);
    }

    /**
     * @param message error message.
     * @param cause   error.
     */
    public IllegalSpecificationException(final String message,
                                         final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause error.
     */
    public IllegalSpecificationException(final Throwable cause) {
        super(cause);
    }
}
