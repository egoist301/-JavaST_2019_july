package by.training.matrix.view;

import by.training.matrix.bean.Matrix;
import by.training.matrix.controller.LoaderFromFile;

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
