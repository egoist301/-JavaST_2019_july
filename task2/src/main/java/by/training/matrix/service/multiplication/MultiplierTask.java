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
     * Constructor.
     *
     * @param matrixFirstNew  first matrix.
     * @param matrixSecondNew second matrix.
     * @param matrixNew       result matrix.
     * @param startRowNew     initial boundary for each thread.
     * @param endRowNew       end boundary for each thread.
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
        int countColumns = matrix.getCountColumns();
        for (int i = startRow; i <= endRow; ++i) {
            for (int j = 0; j < countColumns; ++j) {
                matrix.setElement(i, j, calcSingleValue(i, j));
            }
        }

    }

    /**
     * Calculate element of result matrix.
     *
     * @param row initial boundary for each thread.
     * @param col end boundary for each thread.
     * @return element of result matrix.
     */
    private int calcSingleValue(final int row, final int col) {
        int result = 0;
        int countRows = matrixSecond.getCountRows();
        for (int i = 0; i < countRows; ++i) {
            result += matrixFirst.getElement(row, i)
                    * matrixSecond.getElement(i, col);
        }
        return result;
    }
}
