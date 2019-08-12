package by.training.matrix.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Thread validation.
 */
public final class ThreadValidation {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Default constructor.
     */
    private ThreadValidation() {

    }

    /**
     * Check for integer.
     *
     * @param element string element.
     * @return true or not.
     */
    private static boolean isInteger(final String element) {
        try {
            Integer.parseInt(element);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Incorrect value.", ex);
            return false;
        }
        return true;
    }

    /**
     * Check correct count of threads.
     * @param listNew list of arrays of strings.
     * @return equal or not equal.
     */
    public static boolean isCorrectCountOfThreads(final
                                                  List<String[]> listNew) {
        final int max = 6;
        final int min = 4;
        int size = listNew.size();
        return size <= min || size >= max;
    }

    /**
     * Check unique number or not.
     *
     * @param listNew list of arrays of strings.
     * @return equal or not equal.
     */
    public static boolean isUniqueNumbers(final List<String[]> listNew) {
        for (int i = 0; i < listNew.size(); ++i) {
            if (isInteger(listNew.get(i)[0])) {
                int temp = Integer.parseInt(listNew.get(i)[0]);
                for (int j = 0; i < listNew.size(); ++j) {
                    if (temp == Integer.parseInt(listNew.get(j)[0])) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
