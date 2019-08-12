package by.training.matrix.view.menu;

import by.training.matrix.bean.Matrix;
import by.training.matrix.controller.SimpleMultiplierButton;

/**
 * Simple multiplier.
 */
public class SimpleMultiplier extends MenuEntry {
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
    SimpleMultiplier(final String titleNew,
                     final Matrix matrixFirstNew,
                     final Matrix matrixSecondNew) {
        super(titleNew);
        matrixFirst = matrixFirstNew;
        matrixSecond = matrixSecondNew;
    }

    /**
     * Multiplication two matrices.
     */
    @Override
    public void run() {
        new SimpleMultiplierButton().multiply(matrixFirst, matrixSecond);
    }
}
