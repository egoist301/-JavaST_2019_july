package by.training.matrix.service.diagonal;

import by.training.matrix.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.SecureRandom;
import java.util.Random;
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
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Lock.
     */
    private Lock lock;

    /**
     * Constructor.
     *
     * @param matrixNew matrix.
     * @param numberNew number-element.
     * @param lockNew   lock.
     */
    DiagonalTaskWithLock(final Matrix matrixNew,
                         final int numberNew, final Lock lockNew) {
        matrix = matrixNew;
        number = numberNew;
        lock = lockNew;
    }

    /**
     * Method run.
     */
    @Override
    public void run() {
        final int time = 50;
        Random random = new SecureRandom();
        int size = matrix.getCountRows();
        for (int i = 0; i < size; ++i) {
            lock.lock();
            if (matrix.getElement(i, i) == 0) {
                matrix.setElement(i, i, number);
            }
            lock.unlock();
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(time));
            } catch (InterruptedException eNew) {
                LOGGER.error(eNew);
                Thread.currentThread().interrupt();
            }
        }
    }
}
