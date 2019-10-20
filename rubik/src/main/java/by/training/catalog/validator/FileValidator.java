package by.training.catalog.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * A validation class that can be used to check if file with chosen name
 * exists and is not a directory.
 */
public final class FileValidator {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Checks if file with chosen name exists and is not directory.
     *
     * @param filename name of the file.
     * @return {@code true} is file exists and is not directory. If filename
     * is null, returns {@code false}.
     */
    public boolean validate(final String filename) {

        if (filename == null) {
            LOGGER.warn(
                    "filename argument is null, validation result is false");
            return false;
        }
        LOGGER.debug(filename, "argument ");
        File file = new File(filename);
        boolean result = file.exists() && file.isFile();
        String info = filename + " validation result is " + result;
        LOGGER.info(info);
        return result;
    }
}
