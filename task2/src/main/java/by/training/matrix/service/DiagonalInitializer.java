package by.training.matrix.service;

import by.training.matrix.bean.Matrix;

import java.util.concurrent.Semaphore;

public class DiagonalInitializer {
    /**
     * Matrix.
     */
    private Matrix matrix;
    /**
     * Count of threads.
     */
    private int countThreads;

    /**
     * @param matrixNew matrix.
     * @param countThreadsNew count of threads.
     */
    public DiagonalInitializer(final Matrix matrixNew,
                               final int countThreadsNew) {
        matrix = matrixNew;
        countThreads = countThreadsNew;
    }

    /**
     * Initialize matrix.
     */
    public void initializeMatrix() {
        Semaphore semaphore = new Semaphore(1);
        Thread[] threads = new Thread[countThreads];

        for (int i = 0; i < countThreads; ++i) {
            threads[i] = new Thread(new DiagonalTask(matrix, i + 1, semaphore));
        }
        for (int i = 0; i < countThreads; ++i) {
            threads[i].start();
        }
    }
}
