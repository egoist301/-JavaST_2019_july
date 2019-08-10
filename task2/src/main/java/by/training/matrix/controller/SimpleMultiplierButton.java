package by.training.matrix.controller;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.SimpleMatrixMultiplication;
import by.training.matrix.service.exception.MatrixValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Simple multiplication button.
 */
public class SimpleMultiplierButton {
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

        try {
            new PrintMatrixButton().printMatrix(
                    new SimpleMatrixMultiplication(matrixFirstNew,
                            matrixSecondNew).multiplication());
        } catch (MatrixValidationException eNew) {
            LOGGER.error(eNew);
            throw new RuntimeException(eNew);
        }
    }
}
