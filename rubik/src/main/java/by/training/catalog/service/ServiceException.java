package by.training.catalog.service;

/**
 * Service exception. Catch Persistent exception.
 */
public class ServiceException extends Exception {
    /**
     * Default constructor.
     */
    public ServiceException() {
    }

    /**
     * Constructor with error message.
     * @param message error message.
     */
    public ServiceException(final String message) {
        super(message);
    }

    /**
     * Constructor with all parameters.
     * @param message error message.
     * @param cause error.
     */
    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     * @param cause error.
     */
    public ServiceException(final Throwable cause) {
        super(cause);
    }
}
