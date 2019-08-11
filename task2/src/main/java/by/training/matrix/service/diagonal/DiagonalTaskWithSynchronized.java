package by.training.matrix.service.diagonal;

import by.training.matrix.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Diagonal task with synchronized.
 */
public class DiagonalTaskWithSynchronized implements Runnable {
    /**
     * Matrix.
     */
    private Matrix matrix;
    /**
     * Number element.
     */
    private int number;
    /**
     * Count elements for thread.
     */
    private int countElements;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @param matrixNew        matrix.
     * @param numberNew        number-element.
     * @param countElementsNew count elements.
     */

    DiagonalTaskWithSynchronized(final Matrix matrixNew,
                                 final int numberNew,
                                 final int countElementsNew) {
        matrix = matrixNew;
        number = numberNew;
        countElements = countElementsNew;
    }

    /**
     * run.
     */
    @Override
    public void run() {
        int temp = 0;
        final int time = 50;
        for (int i = 0; i < matrix.getCountRows(); ++i) {
            synchronized (DiagonalTaskWithSynchronized.class) {
                if (matrix.getElement(i, i) == 0) {
                    matrix.setElement(i, i, number);
                    ++temp;
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException eNew) {
                LOGGER.error(eNew);
                Thread.currentThread().interrupt();
            }
            if (temp == countElements) {
                break;
            }
        }
    }
}
