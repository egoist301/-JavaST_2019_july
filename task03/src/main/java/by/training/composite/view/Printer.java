package by.training.composite.view;

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

    /**
     * Default constructor.
     */
    private Printer() {
    }

    /**
     * Print info on console.
     *
     * @param message message to print.
     */
    public static void printInfo(final Object message) {
        LOGGER.info(message);
    }
}
