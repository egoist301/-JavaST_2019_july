package by.training.matrix.controller;

import by.training.matrix.bean.Matrix;

/**
 * Diagonal transformation button.
 */
public class DiagonalTransformationButton {
    /**
     * @param matrixNew matrix.
     */
    public void transformate(final Matrix matrixNew) {
        new PrintMatrixButton().printMatrix(matrixNew);
    }
}
