package by.training.matrix.controller;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.diagonal.DiagonalInitializable;
import by.training.matrix.service.diagonal.DiagonalInitializerWithLock;

/**
 * Diagonal transformation button.
 */
public class DiagonalTransformationButton {
    /**
     * Initialize diagonal elements.
     *
     * @param matrixNew      matrix.
     * @param elementNumbers element numbers.
     */
    public void execute(final Matrix matrixNew, final int[] elementNumbers) {

        DiagonalInitializable initializer =
                new DiagonalInitializerWithLock(matrixNew,
                        elementNumbers.length, elementNumbers);
        initializer.initializeDiagonal();
    }
}
