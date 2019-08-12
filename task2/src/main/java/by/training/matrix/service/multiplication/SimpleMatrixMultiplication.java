package by.training.matrix.service.multiplication;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.MatrixValidation;
import by.training.matrix.service.exception.MatrixValidationException;

/**
 * Single-threaded multiplication.
 */
public class SimpleMatrixMultiplication implements Multiplication {
    /**
     * First matrix.
     */
    private Matrix matrixFirst;
    /**
     * Second matrix.
     */
    private Matrix matrixSecond;

    /**
     * Constructor.
     *
     * @param matrixFirstNew  first matrix.
     * @param matrixSecondNew second matrix.
     */
    public SimpleMatrixMultiplication(final Matrix matrixFirstNew,
                                      final Matrix matrixSecondNew) {
        matrixFirst = matrixFirstNew;
        matrixSecond = matrixSecondNew;
    }

    /**
     * Multiplication two matrix.
     *
     * @return result multiplication(matrix).
     * @throws MatrixValidationException custom exception.
     */
    @Override
    public Matrix multiplication()
            throws MatrixValidationException {
        if (MatrixValidation.isMultiplicableMatrices(matrixFirst,
                matrixSecond)) {
            Matrix matrix = new Matrix(matrixFirst.getCountRows(),
                    matrixSecond.getCountColumns());
            int rowsFirstMatrix = matrixFirst.getCountRows();
            int columnsSecondMatrix = matrixSecond.getCountColumns();
            int columnsFirstMatrix = matrixFirst.getCountColumns();
            for (int i = 0; i < rowsFirstMatrix; ++i) {
                for (int j = 0; j < columnsSecondMatrix; ++j) {
                    int temp = 0;
                    for (int k = 0; k < columnsFirstMatrix; ++k) {
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
