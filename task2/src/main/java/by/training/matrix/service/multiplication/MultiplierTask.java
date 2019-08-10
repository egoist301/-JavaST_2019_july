package by.training.matrix.service.multiplication;

import by.training.matrix.bean.Matrix;

/**
 * Multiplication with implements Runnable.
 */
public class MultiplierTask implements Runnable {
    /**
     * First matrix.
     */
    private Matrix matrixFirst;
    /**
     * Second matrix.
     */
    private Matrix matrixSecond;
    /**
     * Initial boundary for each thread.
     */
    private int startRow;
    /**
     * End boundary for each thread.
     */
    private int endRow;
    /**
     * Result matrix.
     */
    private Matrix matrix;

    /**
     * @param matrixFirstNew  first matrix.
     * @param matrixSecondNew second matrix.
     * @param matrixNew result matrix.
     * @param startRowNew initial boundary for each thread.
     * @param endRowNew end boundary for each thread.
     */
    MultiplierTask(final Matrix matrixFirstNew,
                   final Matrix matrixSecondNew,
                   final Matrix matrixNew, final int startRowNew,
                   final int endRowNew) {
        matrix = matrixNew;
        matrixFirst = matrixFirstNew;
        matrixSecond = matrixSecondNew;
        startRow = startRowNew;
        endRow = endRowNew;
    }

    /**
     * Method run.
     */
    @Override
    public void run() {
        for (int i = startRow; i <= endRow; ++i) {
            for (int j = 0; j < matrix.getCountColumns(); ++j) {
                matrix.setElement(i, j, calcSingleValue(i, j));
            }
        }

    }

    /**
     * @param row initial boundary for each thread.
     * @param col end boundary for each thread.
     * @return element of result matrix.
     */
    private int calcSingleValue(final int row, final int col) {
        int result = 0;
        for (int i = 0; i < matrixSecond.getCountRows(); ++i) {
            result += matrixFirst.getElement(row, i)
                    * matrixSecond.getElement(i, col);
        }
        return result;
    }
}
