package by.training.matrix.controller;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.MatrixInitializer;

/**
 * Diagonal initializer zero.
 */
public class DiagonalInitializerZero {
    /**
     * @param matrixNew matrix.
     */
    public void execute(final Matrix matrixNew) {
        MatrixInitializer.initializeDiagonalZero(matrixNew);
    }
}
