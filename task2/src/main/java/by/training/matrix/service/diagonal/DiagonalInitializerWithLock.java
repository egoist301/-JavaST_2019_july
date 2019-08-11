package by.training.matrix.service.diagonal;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.MatrixInitializer;
import by.training.matrix.service.MatrixValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Diagonal initializer with lock.
 */
public class DiagonalInitializerWithLock {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Matrix.
     */
    private Matrix matrix;
    /**
     * Count of threads.
     */
    private int countThreads;

    /**
     * @param matrixNew       matrix.
     * @param countThreadsNew count of threads.
     */
    public DiagonalInitializerWithLock(final Matrix matrixNew,
                                       final int countThreadsNew) {
        matrix = matrixNew;
        countThreads = countThreadsNew;
    }

    /**
     * Initialize matrix.
     */
    public void initializeMatrix() {
        if (MatrixValidation.isSquareMatrix(matrix)) {
            MatrixInitializer.initializeDiagonalZero(matrix);
            Lock lock = new ReentrantLock();
            int countElements = matrix.getCountRows() / countThreads;
            int additional = matrix.getCountRows() % countThreads;
            Thread[] threads = new Thread[countThreads];
            int temp = 1;
            for (int i = 0; i < countThreads; ++i) {
                if (additional == 0) {
                    temp = 0;
                } else {
                    --additional;
                }
                int count = countElements + temp;
                threads[i] = new Thread(new DiagonalTaskWithLock(matrix, i + 1,
                        count, lock));
            }
            for (int i = 0; i < countThreads; ++i) {
                threads[i].start();
            }
        } else {
            LOGGER.error("Non square matrix");
            throw new RuntimeException();
        }
    }
}
