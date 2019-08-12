package by.training.matrix.service.multiplication;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.MatrixValidation;
import by.training.matrix.service.exception.MatrixValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Multiplication matrix with thread.
 */
public class MatrixMultiplierWithThread implements Multiplication {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * First matrix.
     */
    private Matrix matrixFirst;
    /**
     * Second matrix.
     */
    private Matrix matrixSecond;
    /**
     * Count of threads.
     */
    private int countThreads;

    /**
     * Constructor.
     *
     * @param matrixFirstNew  first matrix.
     * @param matrixSecondNew second matrix.
     * @param countThreadsNew count of threads.
     */
    public MatrixMultiplierWithThread(final Matrix matrixFirstNew,
                                      final Matrix matrixSecondNew,
                                      final int countThreadsNew) {
        matrixFirst = matrixFirstNew;
        matrixSecond = matrixSecondNew;
        countThreads = countThreadsNew;
    }

    /**
     * Multiplication two matrix.
     *
     * @return result matrix.
     * @throws MatrixValidationException custom exception.
     */
    @Override
    public Matrix multiplication() throws MatrixValidationException {
        if (MatrixValidation.isMultiplicableMatrices(matrixFirst,
                matrixSecond)) {
            if (countThreads > matrixFirst.getCountRows()) {
                countThreads = matrixFirst.getCountRows();
            }
            Matrix matrix = new Matrix(matrixFirst.getCountRows(),
                    matrixSecond.getCountColumns());
            Thread[] threads = new Thread[countThreads];
            initThreads(threads, matrix);
            startThread(threads);
            joinThread(threads);
            return matrix;
        } else {
            throw new MatrixValidationException();
        }
    }

    /**
     * Initialize array threads.
     *
     * @param threadsNew array threads.
     * @param matrixNew  matrix.
     */
    private void initThreads(final Thread[] threadsNew,
                             final Matrix matrixNew) {
        int countRows = matrixFirst.getCountRows() / countThreads;
        int additional = matrixFirst.getCountRows() % countThreads;
        int start = 0;
        int temp = 1;
        for (int i = 0; i < countThreads; i++) {
            if (additional == 0) {
                temp = 0;
            } else {
                --additional;
            }
            int count = countRows + temp;
            int end = start + count - 1;
            threadsNew[i] = new Thread(new MultiplierTask(matrixFirst,
                    matrixSecond, matrixNew, start, end));
            start += count;
        }
    }

    /**
     * Start threads.
     *
     * @param threadsNew array of threads.
     */
    private void startThread(final Thread[] threadsNew) {
        for (Thread thread : threadsNew) {
            thread.start();
        }
    }

    /**
     * Join threads.
     *
     * @param threadsNew array of threads.
     */
    private void joinThread(final Thread[] threadsNew) {
        try {
            for (Thread thread : threadsNew) {
                thread.join();
            }
        } catch (InterruptedException e) {
            LOGGER.error(e);
            Thread.currentThread().interrupt();
        }
    }
}
