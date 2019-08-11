package by.training.matrix.controller;

import by.training.matrix.bean.Matrix;
import by.training.matrix.view.Printer;

/**
 * Print matrix button.
 */
public class PrintMatrixButton {
    /**
     * @param matrixNew matrix.
     */
    public void printMatrix(final Matrix matrixNew) {
        Printer.printInfo("Matrix:\n" + matrixNew);
    }
}
