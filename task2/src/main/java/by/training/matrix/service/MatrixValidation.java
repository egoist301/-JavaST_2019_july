package by.training.matrix.service;

import by.training.matrix.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Matrix validation. Util class.
 */
public final class MatrixValidation {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Default constructor.
     */
    private MatrixValidation() {
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
     * Check for multiplication two matrices.
     *
     * @param matrixNewFirst  first matrix.
     * @param matrixNewSecond second matrix.
     * @return true or not.
     */
    public static boolean isMultiplicableMatrices(final Matrix matrixNewFirst,
                                                  final
                                                  Matrix matrixNewSecond) {
        return matrixNewFirst.getCountColumns()
                == matrixNewSecond.getCountRows();
    }

    /**
     * Check for square matrix.
     *
     * @param matrixNew matrix.
     * @return true or not.
     */
    public static boolean isSquareMatrix(final Matrix matrixNew) {
        return matrixNew.getCountRows() == matrixNew.getCountColumns();
    }

    /**
     * Check for matrix.
     *
     * @param listNew list of array string.
     * @return true or not.
     */
    private static boolean isMatrix(final List<String[]> listNew) {
        String[] strings = listNew.get(0);
        for (String[] stringsNew : listNew) {
            if (strings.length != stringsNew.length) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check for integer matrix.
     *
     * @param listNew list of array string.
     * @return true or not.
     */
    public static boolean isIntegerMatrix(final List<String[]> listNew) {
        if (isMatrix(listNew)) {
            for (String[] strings : listNew) {
                for (String string : strings) {
                    if (!isInteger(string)) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            LOGGER.error("This is not a matrix.");
            return false;
        }
    }
}
