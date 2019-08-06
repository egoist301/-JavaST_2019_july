package by.training.matrix.service;

import by.training.matrix.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public final class MatrixValidation {
    private static final Logger LOGGER = LogManager.getLogger();

    private MatrixValidation() {
    }

    private static boolean isInteger(final String element) {
        try {
            Integer.parseInt(element);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Incorrect value.", ex);
            return false;
        }
        return true;
    }

    static boolean isMultiplicableMatrices(final Matrix matrixNewFirst,
                                           final Matrix matrixNewSecond) {
        return matrixNewFirst.getCountColumns()
                == matrixNewSecond.getCountRows();
    }

    private static boolean isMatrix(final List<String[]> listNew) {
        String[] strings = listNew.get(0);
        for (String[] stringsNew : listNew) {
            if (strings.length != stringsNew.length) {
                return false;
            }
        }
        return true;
    }

    public static boolean isIntegerMatrix(final List<String[]> listNew) {
        if (isMatrix(listNew)) {
            for (String[] strings : listNew) {
                for (String string : strings) {
                    if (!isInteger(string)) {
                        LOGGER.error("Incorrect element.");
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
