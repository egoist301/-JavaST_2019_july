package by.training.matrix.service;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.exception.MatrixValidationException;

/**
 * Single-threaded multiplication.
 */
public class SimpleMatrixMultiplication {
    /**
     * First matrix.
     */
    private Matrix matrixFirst;
    /**
     * Second matrix.
     */
    private Matrix matrixSecond;

    /**
     * @param matrixFirstNew first matrix.
     * @param matrixSecondNew second matrix.
     */
    public SimpleMatrixMultiplication(final Matrix matrixFirstNew,
                                      final Matrix matrixSecondNew) {
        matrixFirst = matrixFirstNew;
        matrixSecond = matrixSecondNew;
    }

    /**
     * @return result multiplication(matrix).
     * @throws MatrixValidationException custom exception.
     */
    public Matrix multiplication()
            throws MatrixValidationException {
        if (MatrixValidation.isMultiplicableMatrices(matrixFirst,
                matrixSecond)) {
            Matrix matrix = new Matrix(matrixFirst.getCountRows(),
                    matrixSecond.getCountColumns());
            for (int i = 0; i < matrixFirst.getCountRows(); ++i) {
                for (int j = 0; j < matrixSecond.getCountColumns(); ++j) {
                    int temp = 0;
                    for (int k = 0; k < matrixFirst.getCountColumns(); ++k) {
                        temp += matrixFirst.getElement(i, k)
                                * matrixSecond.getElement(k, j);
                    }
                    matrix.setElement(i, j, temp);
                }
            }
            return matrix;
        } else {
            throw new MatrixValidationException("Unequal sizes.");
        }
    }
}
