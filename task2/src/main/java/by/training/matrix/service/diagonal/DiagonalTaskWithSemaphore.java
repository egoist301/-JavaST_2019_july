package by.training.matrix.service.diagonal;

import by.training.matrix.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.SecureRandom;
import java.util.Random;
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
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Semaphore.
     */
    private Semaphore semaphore;

    /**
     * Constructor.
     *
     * @param matrixNew    matrix.
     * @param numberNew    number-element.
     * @param semaphoreNew semaphore.
     */
    DiagonalTaskWithSemaphore(final Matrix matrixNew,
                              final int numberNew,
                              final Semaphore semaphoreNew) {
        matrix = matrixNew;
        number = numberNew;
        semaphore = semaphoreNew;
    }

    /**
     * Method run.
     */
    @Override
    public void run() {
        Random random = new SecureRandom();
        final int time = 50;
        try {
            int size = matrix.getCountRows();
            for (int i = 0; i < size; ++i) {
                semaphore.acquire();
                if (matrix.getElement(i, i) == 0) {
                    matrix.setElement(i, i, number);
                }
                semaphore.release();
                TimeUnit.MILLISECONDS.sleep(random.nextInt(time));
            }
        } catch (InterruptedException eNew) {
            LOGGER.error(eNew);
            Thread.currentThread().interrupt();
        }
    }
}
