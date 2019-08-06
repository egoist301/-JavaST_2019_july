package by.training.matrix.dao.factory;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.MatrixValidation;
import by.training.matrix.service.exception.MatrixValidationException;

import java.util.List;

public final class FactoryMatrixDAO {
    private static final FactoryMatrixDAO FACTORY = new FactoryMatrixDAO();

    private FactoryMatrixDAO() {
    }

    public static FactoryMatrixDAO getFactory() {
        return FACTORY;
    }

    public Matrix createMatrix(final List<String[]> listNew)
            throws MatrixValidationException {
        if (MatrixValidation.isIntegerMatrix(listNew)) {

            Matrix matrix = new Matrix(listNew.size(), listNew.get(0).length);
            for (int i = 0; i < listNew.size(); ++i) {
                for (int j = 0; j < listNew.get(i).length; ++j) {
                    matrix.setElement(i, j,
                            Integer.parseInt(listNew.get(i)[j]));
                }
            }
            return matrix;
        } else {
            throw new MatrixValidationException("This is not a"
                    + " integer matrix.");
        }
    }
}
