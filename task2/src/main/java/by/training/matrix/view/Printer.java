package by.training.matrix.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Printer.
 */
public final class Printer {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    private Printer() {
    }

    /**
     * @param message message to print.
     */
    public static void printInfo(final Object message) {
        LOGGER.info(message);
    }
}
