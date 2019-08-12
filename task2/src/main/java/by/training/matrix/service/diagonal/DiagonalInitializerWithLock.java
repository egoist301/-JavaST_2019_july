package by.training.matrix.service.diagonal;

import by.training.matrix.bean.Matrix;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Diagonal initializer with lock.
 */
public class DiagonalInitializerWithLock implements DiagonalInitializable {
    /**
     * Matrix.
     */
    private Matrix matrix;
    /**
     * Count of threads.
     */
    private int countThreads;

    /**
     * Constructor.
     *
     * @param matrixNew       matrix.
     * @param countThreadsNew count of threads.
     */
    public DiagonalInitializerWithLock(final Matrix matrixNew,
                                       final int countThreadsNew) {
        matrix = matrixNew;
        countThreads = countThreadsNew;
    }

    /**
     * Initialize matrix diagonal.
     */
    @Override
    public void initializeDiagonal() {
        Lock lock = new ReentrantLock();
        Thread[] threads = new Thread[countThreads];
        for (int i = 0; i < countThreads; ++i) {
            threads[i] = new Thread(new DiagonalTaskWithLock(matrix,
                    i + 1, lock));
        }
        for (int i = 0; i < countThreads; ++i) {
            threads[i].start();
        }
    }
}
