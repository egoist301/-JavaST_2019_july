package by.training.matrix.service.exception;

/**
 * Custom exception.
 */
public class MatrixValidationException extends Exception {
    /**
     * Default constructor.
     */
    public MatrixValidationException() {
    }

    /**
     * @param message error message.
     */
    public MatrixValidationException(final String message) {
        super(message);
    }

    /**
     * @param message error message.
     * @param cause   error.
     */
    public MatrixValidationException(final String message,
                                     final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause error.
     */
    public MatrixValidationException(final Throwable cause) {
        super(cause);
    }
}
