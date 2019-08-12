package by.training.matrix.service.diagonal;

import by.training.matrix.bean.Matrix;

import java.util.concurrent.Semaphore;

/**
 * Diagonal initializer with semaphore.
 */
public class DiagonalInitializerWithSemaphore implements DiagonalInitializable {
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
    public DiagonalInitializerWithSemaphore(final Matrix matrixNew,
                                            final int countThreadsNew) {
        matrix = matrixNew;
        countThreads = countThreadsNew;
    }

    /**
     * Initialize diagonal matrix.
     */
    @Override
    public void initializeDiagonal() {
        Semaphore semaphore = new Semaphore(1);
        Thread[] threads = new Thread[countThreads];
        for (int i = 0; i < countThreads; ++i) {
            threads[i] = new Thread(new DiagonalTaskWithSemaphore(matrix,
                    i + 1, semaphore));
        }
        for (int i = 0; i < countThreads; ++i) {
            threads[i].start();
        }
    }
}
