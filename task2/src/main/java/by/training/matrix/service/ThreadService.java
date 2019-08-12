package by.training.matrix.service;

import by.training.matrix.dao.parser.DataParser;
import by.training.matrix.dao.reader.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Thread service.
 */
public final class ThreadService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Singleton.
     */
    private static final ThreadService THREAD_SERVICE = new ThreadService();

    /**
     * Default constructor.
     */
    private ThreadService() {
    }

    /**
     * Singleton.
     *
     * @return thread service.
     */
    public static ThreadService getThreadService() {
        return THREAD_SERVICE;
    }

    /**
     * Create unique number.
     *
     * @param filepath filepath.
     * @return matrix.
     */
    public int[] createUniqueNumber(final String filepath) {
        DataReader dataReader = new DataReader();
        DataParser dataParser = new DataParser();
        List<String[]> list = dataParser.getLines(dataReader.readAll(filepath));
        if (ThreadValidation.isCorrectCountOfThreads(list)) {
            if (ThreadValidation.isUniqueNumbers(list)) {
                int[] array = new int[list.size()];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = Integer.parseInt(list.get(i)[0]);
                }
                return array;
            } else {
                LOGGER.warn("Duplicate.");
                throw new RuntimeException();
            }
        } else {
            LOGGER.error("Incorrect count of threads.");
            throw new RuntimeException();
        }
    }
}
