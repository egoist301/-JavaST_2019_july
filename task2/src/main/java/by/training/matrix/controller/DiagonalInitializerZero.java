package by.training.matrix.controller;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.MatrixInitializer;

/**
 * Diagonal initializer zero.
 */
public class DiagonalInitializerZero implements Executable {
    /**
     * Initialize diagonal elements zero.
     * @param matrixNew matrix.
     */
    @Override
    public void execute(final Matrix matrixNew) {
        MatrixInitializer.initializeDiagonalZero(matrixNew);
    }
}
