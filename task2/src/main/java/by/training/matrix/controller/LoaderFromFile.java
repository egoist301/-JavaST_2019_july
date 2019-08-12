package by.training.matrix.controller;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.factory.FactoryMatrix;

/**
 * Loader from file.
 */
public class LoaderFromFile {
    /**
     * Load data from file.
     *
     * @param filePath filepath.
     * @return matrix.
     */
    public Matrix load(final String filePath) {
        return FactoryMatrix.getFactoryMatrix().createMatrix(filePath);
    }
}
