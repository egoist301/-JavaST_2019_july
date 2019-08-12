package by.training.matrix.controller;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.exception.MatrixValidationException;
import by.training.matrix.service.multiplication.SimpleMatrixMultiplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Simple multiplication button.
 */
public class SimpleMultiplierButton implements MultiplicationButton {
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

        try {
            new PrintMatrixButton().execute(
                    new SimpleMatrixMultiplication(matrixFirstNew,
                            matrixSecondNew).multiplication());
        } catch (MatrixValidationException eNew) {
            LOGGER.error(eNew);
            throw new RuntimeException(eNew);
        }
    }
}
