package by.training.matrix.view.menu;

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
     * Constructor.
     *
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
     * Print two matrix.
     */
    @Override
    public void run() {
        PrintMatrixButton printMatrixButton = new PrintMatrixButton();
        printMatrixButton.execute(matrixFirst);
        printMatrixButton.execute(matrixSecond);
    }
}
