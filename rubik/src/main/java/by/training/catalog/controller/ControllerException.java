package by.training.catalog.controller;

public class ControllerException extends Exception {
    public ControllerException() {
    }

    public ControllerException(final String message) {
        super(message);
    }

    public ControllerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ControllerException(final Throwable cause) {
        super(cause);
    }
}
