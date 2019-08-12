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
     * Element numbers.
     */
    private int[] elementNumbers;

    /**
     * Constructor.
     *
     * @param titleNew  name menu entry.
     * @param matrixNew matrix.
     * @param elementNumbersNew element numbers.
     */
    public DiagonalTransformation(final String titleNew,
                                  final Matrix matrixNew,
                                  final int[] elementNumbersNew) {
        super(titleNew);
        matrix = matrixNew;
        elementNumbers = elementNumbersNew;
    }

    /**
     * Diagonal initialize.
     */
    @Override
    public void run() {
        new DiagonalTransformationButton().execute(matrix, elementNumbers);
    }
}
