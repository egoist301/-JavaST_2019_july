package by.training.composite.dao.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Data reader.
 */
public class DataReader {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Read from file and return string..
     *
     * @param filePath the path of the file from which we read.
     * @return string..
     */
    public String readAll(final String filePath) {
        String lines;
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuilder.append(lines + "\n");
            }
        } catch (IOException ex) {
            LOGGER.fatal("Error while opening file", ex);
            throw new RuntimeException("Error while opening file", ex);
        }
        return stringBuilder.toString();
    }
}
