package by.training.matrix.service;

import by.training.matrix.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to initialize the matrix.
 */
public final class MatrixInitializer {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Default constructor.
     */
    private MatrixInitializer() {
    }

    /**
     * Method to initialize.
     *
     * @param matrix matrix.
     */
    public static void initialize(final Matrix matrix) {
        for (int i = 0; i < matrix.getCountRows(); ++i) {
            for (int j = 0; j < matrix.getCountColumns(); ++j) {
                matrix.setElement(i, j, i + 2);
            }
        }
    }

    /**
     * @param matrixNew square matrix.
     */
    public static void initializeDiagonalZero(final Matrix matrixNew) {
        if (MatrixValidation.isSquareMatrix(matrixNew)) {
            for (int i = 0; i < matrixNew.getCountRows(); ++i) {
                matrixNew.setElement(i, i, 0);
            }
        } else {
            LOGGER.error("non square matrix.");
            throw new RuntimeException();
        }
    }
}
