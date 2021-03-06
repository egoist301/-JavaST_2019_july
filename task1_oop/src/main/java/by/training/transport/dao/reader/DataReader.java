package by.training.transport.dao.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DataReader {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @param filePath the path of the file from which we read.
     * @return list of strings.
     */
    public List<String> readAll(final String filePath) {
        List<String> lines;
        try (FileReader reader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            lines = bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            LOGGER.fatal("Error while opening file", ex);
            throw new RuntimeException("Error while opening file", ex);
        }
        return lines;
    }
}
