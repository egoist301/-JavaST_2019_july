package by.training.matrix.service;

import by.training.matrix.bean.Matrix;

public final class MatrixInitializer {
    private MatrixInitializer() {
    }

    public static void initialize(final Matrix matrix) {
        for (int i = 0; i < matrix.getCountRows(); i++) {
            for (int j = 0; j < matrix.getCountColumns(); j++) {
                matrix.setElement(i, j, i + 2);
            }
        }
    }
}
