package by.training.transport.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public static void execute(final Object message) {
        LOGGER.info(message);
    }
}
