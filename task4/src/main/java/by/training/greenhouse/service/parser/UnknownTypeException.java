package by.training.greenhouse.service.parser;

/**
 * Unknown type exception class.
 */
public class UnknownTypeException extends Exception {
    /**
     * Constructor without parameters.
     */
    public UnknownTypeException() {
        super();
    }

    /**
     * Constr. with one parameter.
     *
     * @param message message.
     */
    public UnknownTypeException(final String message) {
        super(message);
    }

    /**
     * Constr. with two parameters.
     *
     * @param message message.
     * @param cause   cause.
     */
    public UnknownTypeException(final String message,
                                final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constr. with one parameter.
     *
     * @param cause cause.
     */
    public UnknownTypeException(final Throwable cause) {
        super(cause);
    }
}
