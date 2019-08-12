package by.training.matrix.view.menu;

import by.training.matrix.bean.Matrix;
import by.training.matrix.controller.PrintMatrixButton;

/**
 * Print matrix.
 */
public class PrintMatrix extends MenuEntry {
    /**
     * Matrix.
     */
    private Matrix matrix;

    /**
     * Constructor.
     *
     * @param titleNew  name menu entry.
     * @param matrixNew matrix.
     */
    public PrintMatrix(final String titleNew, final Matrix matrixNew) {
        super(titleNew);
        matrix = matrixNew;
    }

    /**
     * Print matrix..
     */
    @Override
    public void run() {
        new PrintMatrixButton().execute(matrix);
    }
}
