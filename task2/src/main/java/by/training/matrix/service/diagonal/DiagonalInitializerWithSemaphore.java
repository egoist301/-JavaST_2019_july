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
    public DiagonalInitializerWithSemaphore(final Matrix matrixNew,
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
        Semaphore semaphore = new Semaphore(1);
        Thread[] threads = new Thread[countThreads];
        for (int i = 0; i < countThreads; ++i) {
            threads[i] = new Thread(new DiagonalTaskWithSemaphore(matrix,
                    numberElementOfThread[i], semaphore));
        }
        for (int i = 0; i < countThreads; ++i) {
            threads[i].start();
        }
    }
}
