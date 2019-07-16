package by.training.transport.bean.factory.exception;

public class CarriageValidationException extends Exception {
    /**
     * Default constructor.
     */
    public CarriageValidationException() {
    }

    /**
     * @param message error message.
     */
    public CarriageValidationException(final String message) {
        super(message);
    }

    /**
     * @param message error message.
     * @param cause error.
     */
    public CarriageValidationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause error.
     */
    public CarriageValidationException(final Throwable cause) {
        super(cause);
    }
}
