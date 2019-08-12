package by.training.matrix.service.multiplication;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.exception.MatrixValidationException;

/**
 * Multiplication interface contains method multiplication.
 */
public interface Multiplication {
    /**
     * @return result matrix.
     * @throws MatrixValidationException custom exception.
     */
    Matrix multiplication() throws MatrixValidationException;
}
