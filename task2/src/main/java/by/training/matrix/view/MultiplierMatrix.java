package by.training.matrix.view;

import by.training.matrix.bean.Matrix;

/**
 * Multiplier matrix.
 */
public class MultiplierMatrix extends MenuEntry {
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
    MultiplierMatrix(final String titleNew,
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
        Menu menu = new Menu();
        menu.addEntry(new PrintMatrixForMultiplication("matrix's",
                matrixFirst, matrixSecond));
        menu.addEntry(new SimpleMultiplier("simple thread",
                matrixFirst, matrixSecond));
        menu.addEntry(new MultiMultiplier("multi thread", matrixFirst,
                matrixSecond));
        menu.run();
    }
}
