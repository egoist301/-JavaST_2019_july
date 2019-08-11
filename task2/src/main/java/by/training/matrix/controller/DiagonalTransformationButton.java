package by.training.matrix.controller;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.diagonal.DiagonalInitializerWithLock;

/**
 * Diagonal transformation button.
 */
public class DiagonalTransformationButton {
    /**
     * @param matrixNew matrix.
     */
    public void transformate(final Matrix matrixNew) {
        final int countOfThreads = 6;
        DiagonalInitializerWithLock initializer =
                new DiagonalInitializerWithLock(matrixNew, countOfThreads);
        initializer.initializeMatrix();
    }
}
