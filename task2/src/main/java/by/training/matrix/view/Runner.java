package by.training.matrix.view;

import by.training.matrix.bean.Matrix;
import by.training.matrix.controller.DiagonalInitializerZero;
import by.training.matrix.controller.LoaderFromFile;
import by.training.matrix.view.menu.DiagonalTransformation;
import by.training.matrix.view.menu.Menu;
import by.training.matrix.view.menu.MultiplierMatrix;
import by.training.matrix.view.menu.PrintMatrix;

/**
 * Runner.
 */
public final class Runner {
    /**
     * Default constructor.
     */
    private Runner() {

    }

    /**
     * @param args command line.
     */
    public static void main(final String[] args) {
        LoaderFromFile loader = new LoaderFromFile();
        Matrix matrix = loader.load("data//matrix.txt");
        DiagonalInitializerZero initializer = new DiagonalInitializerZero();
        initializer.execute(matrix);
        Matrix matrix1 = loader.load("data//firstMatrix.txt");
        Matrix matrix2 = loader.load("data//secondMatrix.txt");
        Menu menu = new Menu();
        menu.addEntry(new PrintMatrix("print main matrix", matrix));
        menu.addEntry(new MultiplierMatrix("multiplier matrix",
                matrix1, matrix2));
        menu.addEntry(new DiagonalTransformation("diagonal of matrix",
                matrix));
        menu.run();
    }
}
