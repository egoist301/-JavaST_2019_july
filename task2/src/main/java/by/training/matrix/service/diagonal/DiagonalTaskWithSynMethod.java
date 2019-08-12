package by.training.matrix.service.diagonal;

import by.training.matrix.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Diagonal task with synchronized method.
 */
public class DiagonalTaskWithSynMethod implements Runnable {
    /**
     * Matrix.
     */
    private Matrix matrix;
    /**
     * Number element.
     */
    private int number;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Constructor.
     *
     * @param matrixNew matrix.
     * @param numberNew number-element.
     */

    DiagonalTaskWithSynMethod(final Matrix matrixNew,
                              final int numberNew) {
        matrix = matrixNew;
        number = numberNew;
    }

    /**
     * Method run.
     */
    @Override
    public void run() {
        final int time = 50;
        int size = matrix.getCountRows();
        for (int i = 0; i < size; ++i) {
            matrix.setElement(i, i, number);
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException eNew) {
                LOGGER.error(eNew);
                Thread.currentThread().interrupt();
            }
        }
    }
}
