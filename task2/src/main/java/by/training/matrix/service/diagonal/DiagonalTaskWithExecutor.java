package by.training.matrix.service.diagonal;

import by.training.matrix.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Diagonal task with executor.
 */
public class DiagonalTaskWithExecutor implements Runnable {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Number-element.
     */
    private int number;
    /**
     * target matrix.
     */
    private Matrix matrix;

    /**
     * List of locks associated with rows.
     */
    private List<Lock> locks;

    /**
     * Constructor.
     *
     * @param numberNew number-element.
     * @param newMatrix matrix.
     * @param newLocks  locks.
     */
    DiagonalTaskWithExecutor(final int numberNew, final Matrix newMatrix,
                             final List<Lock> newLocks) {
        number = numberNew;
        matrix = newMatrix;
        locks = newLocks;
    }

    /**
     * Method run.
     */
    @Override
    public void run() {
        Random random = new SecureRandom();
        final int time = 50;
        int i = 0;
        int matrixSize = matrix.getCountRows();
        while (i < matrixSize
                && (matrix.getElement(i, i) != 0
                || !locks.get(i).tryLock())) {
            i++;
        }
        matrix.setElement(i, i, number);
        locks.get(i).unlock();
        try {
            TimeUnit.MICROSECONDS.sleep(random.nextInt(time));
        } catch (InterruptedException e) {
            LOGGER.warn("error during sleeping", e);
            Thread.currentThread().interrupt();
        }
    }
}
