package by.training.matrix.view.menu;

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
     * Constructor.
     *
     * @param titleNew  name menu entry.
     * @param matrixNew matrix.
     */
    public DiagonalTransformation(final String titleNew,
                                  final Matrix matrixNew) {
        super(titleNew);
        matrix = matrixNew;
    }

    /**
     * Diagonal initialize.
     */
    @Override
    public void run() {
        new DiagonalTransformationButton().execute(matrix);
    }
}
