package by.training.matrix.controller;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.factory.FactoryMatrix;

/**
 * Loader from file.
 */
public class LoaderFromFile {
    /**
     * @param filepath filepath.
     * @return matrix.
     */
    public Matrix load(final String filepath) {
        return FactoryMatrix.getFactoryMatrix().createMatrix(filepath);
    }
}
