package by.training.matrix.service.diagonal;

import by.training.matrix.bean.Matrix;

import java.util.concurrent.Semaphore;

/**
 * Diagonal initializer with semaphore.
 */
public class DiagonalInitializerWithSemaphore {
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
    public DiagonalInitializerWithSemaphore(final Matrix matrixNew,
                                            final int countThreadsNew) {
        matrix = matrixNew;
        countThreads = countThreadsNew;
    }

    /**
     * Initialize matrix.
     */
    public void initializeDiagonal() {
        Semaphore semaphore = new Semaphore(1);
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
            threads[i] = new Thread(new DiagonalTaskWithSemaphore(matrix,
                    i + 1, count, semaphore));
        }
        for (int i = 0; i < countThreads; ++i) {
            threads[i].start();
        }
    }
}
