package by.training.matrix.view;

import by.training.matrix.bean.Matrix;
import by.training.matrix.controller.DiagonalTransformationButton;

/**
 * Diagonal transformation.
 */
public class DiagonalTransformation extends MenuEntry {
    /**
     * Matrix.
     */
    private Matrix matrix;

    /**
     * @param titleNew name menu entry.
     * @param matrixNew matrix.
     */
    DiagonalTransformation(final String titleNew,
                           final Matrix matrixNew) {
        super(titleNew);
        matrix = matrixNew;
    }

    /**
     * run.
     */
    @Override
    public void run() {
        new DiagonalTransformationButton().transformate(matrix);
    }
}
