package by.training.matrix.service.diagonal;

import by.training.matrix.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Diagonal task with lock.
 */
public class DiagonalTaskWithLock implements Runnable {
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
     * Lock.
     */
    private Lock lock;

    /**
     * @param matrixNew        matrix.
     * @param numberNew        number-element.
     * @param countElementsNew count elements.
     * @param lockNew          lock.
     */
    DiagonalTaskWithLock(final Matrix matrixNew,
                         final int numberNew, final int countElementsNew,
                         final Lock lockNew) {
        matrix = matrixNew;
        number = numberNew;
        countElements = countElementsNew;
        lock = lockNew;
    }

    /**
     * run.
     */
    @Override
    public void run() {
        int temp = 0;
        final int time = 50;
        for (int i = 0; i < matrix.getCountRows(); ++i) {
            lock.lock();
            if (matrix.getElement(i, i) == 0) {
                matrix.setElement(i, i, number);
                ++temp;
            }
            lock.unlock();
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
