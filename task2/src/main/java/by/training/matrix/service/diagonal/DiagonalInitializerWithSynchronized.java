package by.training.matrix.service.diagonal;

import by.training.matrix.bean.Matrix;

/**
 * Diagonal initializer with synchronized.
 */
public class DiagonalInitializerWithSynchronized {
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
    public DiagonalInitializerWithSynchronized(
            final Matrix matrixNew, final int countThreadsNew) {
        matrix = matrixNew;
        countThreads = countThreadsNew;
    }

    /**
     * Initialize matrix.
     */
    public void initializeDiagonal() {
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
            threads[i] = new Thread(new DiagonalTaskWithSynchronized(matrix,
                    i + 1, count));
        }
        for (int i = 0; i < countThreads; ++i) {
            threads[i].start();
        }
    }
}
