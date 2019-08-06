package by.training.matrix.service.exception;

public class MatrixValidationException extends Exception {
    public MatrixValidationException() {
    }

    public MatrixValidationException(final String message) {
        super(message);
    }

    public MatrixValidationException(final String message,
                                     final Throwable cause) {
        super(message, cause);
    }

    public MatrixValidationException(final Throwable cause) {
        super(cause);
    }
}
