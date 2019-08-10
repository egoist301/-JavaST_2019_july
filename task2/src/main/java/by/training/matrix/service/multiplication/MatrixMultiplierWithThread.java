package by.training.matrix.service.multiplication;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.MatrixValidation;
import by.training.matrix.service.exception.MatrixValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Multiplication matrix with thread.
 */
public class MatrixMultiplierWithThread {
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
     * @return result matrix.
     * @throws MatrixValidationException custom exception.
     */
    public Matrix multiplication() throws MatrixValidationException {
        if (MatrixValidation.isMultiplicableMatrices(matrixFirst,
                matrixSecond)) {
            if (countThreads > matrixFirst.getCountRows()) {
                countThreads = matrixFirst.getCountRows();
            }
            Matrix matrix = new Matrix(matrixFirst.getCountRows(),
                    matrixSecond.getCountColumns());
            int countRows = matrixFirst.getCountRows() / countThreads;
            int additional = matrixFirst.getCountRows() % countThreads;
            Thread[] threads = new Thread[countThreads];
            int start = 0;
            for (int i = 0; i < countThreads; i++) {
                int count = ((i == 0) ? countRows + additional : countRows);
                int end = start + count - 1;
                threads[i] = new Thread(new MultiplierTask(matrixFirst,
                        matrixSecond, matrix, start, end));
                start += count;
            }
            startThread(threads);
            joinThread(threads);
            return matrix;
        } else {
            throw new MatrixValidationException();
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
