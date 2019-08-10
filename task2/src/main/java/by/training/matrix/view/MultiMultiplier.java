package by.training.matrix.view;

import by.training.matrix.bean.Matrix;
import by.training.matrix.controller.MultiMultiplierButton;

/**
 * Multi threads multiplier.
 */
public class MultiMultiplier extends MenuEntry {
    /**
     * First matrix.
     */
    private Matrix matrixFirst;
    /**
     * Second matrix.
     */
    private Matrix matrixSecond;

    /**
     * @param titleNew name menu entry.
     * @param matrixFirstNew first matrix.
     * @param matrixSecondNew second matrix.
     */
    MultiMultiplier(final String titleNew,
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
        new MultiMultiplierButton().multiply(matrixFirst, matrixSecond);
    }
}
