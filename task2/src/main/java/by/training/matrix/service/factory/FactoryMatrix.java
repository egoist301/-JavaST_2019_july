package by.training.matrix.service.factory;

import by.training.matrix.bean.Matrix;
import by.training.matrix.dao.factory.FactoryMatrixDAO;
import by.training.matrix.dao.parser.DataParser;
import by.training.matrix.dao.reader.DataReader;
import by.training.matrix.service.exception.MatrixValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Factory of matrix.
 */
public class FactoryMatrix {
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Singleton.
     */
    private static final FactoryMatrix FACTORY_MATRIX = new FactoryMatrix();

    /**
     * Default constructor.
     */
    private FactoryMatrix() {
    }

    /**
     * @return factory matrix.
     */
    public static FactoryMatrix getFactoryMatrix() {
        return FACTORY_MATRIX;
    }

    /**
     * Create matrix.
     * @param filepath filepath.
     * @return matrix.
     */
    public Matrix createMatrix(final String filepath) {
        DataReader dataReader = new DataReader();
        DataParser dataParser = new DataParser();
        FactoryMatrixDAO factoryMatrixDAO = FactoryMatrixDAO.getFactory();
        Matrix matrix;
        try {
            matrix = factoryMatrixDAO.createMatrix(dataParser
                    .getLines(dataReader.readAll(filepath)));
        } catch (MatrixValidationException eNew){
            LOGGER.warn(eNew);
            throw new RuntimeException();
        }
        return matrix;
    }
}
