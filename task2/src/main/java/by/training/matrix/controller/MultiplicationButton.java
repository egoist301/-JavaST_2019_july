package by.training.matrix.controller;

import by.training.matrix.bean.Matrix;

/**
 * MultiplicationButton interface.
 */
public interface MultiplicationButton {
    /**
     * Multiply two matrices.
     * @param matrixFirstNew first matrix.
     * @param matrixSecondNew second matrix.
     */
    void multiply(Matrix matrixFirstNew, Matrix matrixSecondNew);
}
