package by.training.matrix.service.diagonal;

import by.training.matrix.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Diagonal task with semaphore.
 */
public class DiagonalTaskWithSemaphore implements Runnable {
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
     * Semaphore.
     */
    private Semaphore semaphore;

    /**
     * @param matrixNew    matrix.
     * @param numberNew    number-element.
     * @param countElementsNew count elements.
     * @param semaphoreNew semaphore.
     */
    DiagonalTaskWithSemaphore(final Matrix matrixNew,
                              final int numberNew,
                              final int countElementsNew,
                              final Semaphore semaphoreNew) {
        matrix = matrixNew;
        number = numberNew;
        countElements = countElementsNew;
        semaphore = semaphoreNew;
    }

    /**
     * run.
     */
    @Override
    public void run() {
        final int time = 50;
        try {
            int temp = 0;
            for (int i = 0; i < matrix.getCountRows(); ++i) {
                semaphore.acquire();
                if (matrix.getElement(i, i) == 0) {
                    matrix.setElement(i, i, number);
                    ++temp;
                }
                semaphore.release();
                TimeUnit.MILLISECONDS.sleep(time);
                if (temp == countElements) {
                    break;
                }
            }
        } catch (InterruptedException eNew) {
            LOGGER.error(eNew);
            Thread.currentThread().interrupt();
        }
    }
}
