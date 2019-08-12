package by.training.matrix.service.diagonal;

import by.training.matrix.bean.Matrix;

/**
 * Diagonal initializer with synchronized.
 */
public class DiagonalInitializerWithSynchronized
        implements DiagonalInitializable {
    /**
     * Matrix.
     */
    private Matrix matrix;
    /**
     * Count of threads.
     */
    private int countThreads;
    /**
     * Number element of thread.
     */
    private int[] numberElementOfThread;

    /**
     * Constructor.
     *
     * @param matrixNew                matrix.
     * @param countThreadsNew          count of threads.
     * @param numberElementOfThreadNew number element of thread.
     */
    public DiagonalInitializerWithSynchronized(final Matrix matrixNew,
                                               final int countThreadsNew,
                                               final
                                               int[] numberElementOfThreadNew) {
        matrix = matrixNew;
        countThreads = countThreadsNew;
        numberElementOfThread = numberElementOfThreadNew;
    }

    /**
     * Initialize diagonal matrix.
     */
    @Override
    public void initializeDiagonal() {
        Thread[] threads = new Thread[countThreads];
        for (int i = 0; i < countThreads; ++i) {
            threads[i] = new Thread(new DiagonalTaskWithSynchronized(matrix,
                    numberElementOfThread[i]));
        }
        for (int i = 0; i < countThreads; ++i) {
            threads[i].start();
        }
    }
}
