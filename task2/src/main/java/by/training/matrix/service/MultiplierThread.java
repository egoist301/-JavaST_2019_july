package by.training.matrix.service;

import by.training.matrix.bean.Matrix;

public class MultiplierThread implements Runnable {
    private Matrix matrixFirst;
    private Matrix matrixSecond;
    private int startRow;
    private int endRow;
    private Matrix matrix;

    MultiplierThread(final Matrix matrixFirstNew,
                            final Matrix matrixSecondNew,
                            final Matrix matrixNew, final int startRowNew,
                            final int endRowNew) {
        matrix = matrixNew;
        matrixFirst = matrixFirstNew;
        matrixSecond = matrixSecondNew;
        startRow = startRowNew;
        endRow = endRowNew;
    }

    @Override
    public void run() {
        for (int i = startRow; i <= endRow; ++i) {
            for (int j = 0; j < matrix.getCountColumns(); ++j) {
                matrix.setElement(i, j, calcSingleValue(i, j));
            }
        }

    }

    private int calcSingleValue(final int row, final int col) {
        int result = 0;
        for (int i = 0; i < matrixSecond.getCountRows(); ++i) {
            result += matrixFirst.getElement(row, i)
                    * matrixSecond.getElement(i, col);
        }
        return result;
    }
}
