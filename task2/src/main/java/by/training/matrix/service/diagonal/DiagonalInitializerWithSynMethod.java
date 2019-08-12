package by.training.matrix.service.diagonal;

import by.training.matrix.bean.Matrix;

/**
 * Diagonal initializer with synchronized method.
 */
public class DiagonalInitializerWithSynMethod implements DiagonalInitializable {
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
    public DiagonalInitializerWithSynMethod(
            final Matrix matrixNew, final int countThreadsNew) {
        matrix = matrixNew;
        countThreads = countThreadsNew;
    }

    /**
     * Initialize diagonal matrix.
     */
    @Override
    public void initializeDiagonal() {
        Thread[] threads = new Thread[countThreads];
        for (int i = 0; i < countThreads; ++i) {
            threads[i] = new Thread(new DiagonalTaskWithSynchronized(matrix,
                    i + 1));
        }
        for (int i = 0; i < countThreads; ++i) {
            threads[i].start();
        }
    }
}
