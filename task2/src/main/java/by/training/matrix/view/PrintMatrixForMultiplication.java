package by.training.matrix.view;

import by.training.matrix.bean.Matrix;
import by.training.matrix.controller.PrintMatrixButton;

/**
 * Print matrix for multiplication.
 */
public class PrintMatrixForMultiplication extends MenuEntry {
    /**
     * First matrix.
     */
    private Matrix matrixFirst;
    /**
     * Second matrix.
     */
    private Matrix matrixSecond;

    /**
     * @param titleNew        name menu entry.
     * @param matrixFirstNew  first matrix.
     * @param matrixSecondNew second matrix.
     */
    PrintMatrixForMultiplication(final String titleNew,
                                 final Matrix matrixFirstNew,
                                 final Matrix matrixSecondNew) {
        super(titleNew);
        matrixFirst = matrixFirstNew;
        matrixSecond = matrixSecondNew;
    }

    /**
     * run.
     */
    @Override
    public void run() {
        PrintMatrixButton printMatrixButton = new PrintMatrixButton();
        printMatrixButton.printMatrix(matrixFirst);
        printMatrixButton.printMatrix(matrixSecond);
    }
}
