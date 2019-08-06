package by.training.matrix.service;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.exception.MatrixValidationException;

public class SimpleMatrixMultiplication {
    private Matrix matrixFirst;
    private Matrix matrixSecond;

    public SimpleMatrixMultiplication(final Matrix matrixFirstNew,
                                      final Matrix matrixSecondNew) {
        matrixFirst = matrixFirstNew;
        matrixSecond = matrixSecondNew;
    }

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
