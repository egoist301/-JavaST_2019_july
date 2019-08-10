package by.training.matrix.controller;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.MatrixMultiplierWithThread;
import by.training.matrix.service.exception.MatrixValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Multi multiplier button.
 */
public class MultiMultiplierButton {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @param matrixFirstNew first matrix.
     * @param matrixSecondNew second matrix.
     */
    public void multiply(final Matrix matrixFirstNew,
                         final Matrix matrixSecondNew) {
        final int countThreads = 8;
        try {
            new PrintMatrixButton().printMatrix(
                    new MatrixMultiplierWithThread(matrixFirstNew,
                            matrixSecondNew, countThreads).multiplication());
        } catch (MatrixValidationException eNew) {
            LOGGER.error(eNew);
            throw new RuntimeException(eNew);
        }
    }
}
