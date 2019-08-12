package by.training.matrix.controller;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.exception.MatrixValidationException;
import by.training.matrix.service.multiplication.MatrixMultiplierWithThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Multi multiplier button.
 */
public class MultiMultiplierButton implements MultiplicationButton {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Multiplication two matrices.
     *
     * @param matrixFirstNew  first matrix.
     * @param matrixSecondNew second matrix.
     */
    @Override
    public void multiply(final Matrix matrixFirstNew,
                         final Matrix matrixSecondNew) {
        final int countThreads = 8;
        try {
            new PrintMatrixButton().execute(
                    new MatrixMultiplierWithThread(matrixFirstNew,
                            matrixSecondNew, countThreads).multiplication());
        } catch (MatrixValidationException eNew) {
            LOGGER.error(eNew);
            throw new RuntimeException(eNew);
        }
    }
}
