package by.training.matrix.controller;

import by.training.matrix.bean.Matrix;
import by.training.matrix.view.Printer;

/**
 * Print matrix button.
 */
public class PrintMatrixButton implements Executable {
    /**
     * Print info.
     *
     * @param matrixNew matrix.
     */
    @Override
    public void execute(final Matrix matrixNew) {
        Printer.printInfo("Matrix:\n" + matrixNew);
    }
}
