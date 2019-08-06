package by.training.matrix.service;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.exception.MatrixValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MatrixMultiplierWithThread {
    private static final Logger LOGGER = LogManager.getLogger();
    private Matrix matrixFirst;
    private Matrix matrixSecond;
    private int countThreads;

    public MatrixMultiplierWithThread(final Matrix matrixFirstNew,
                                      final Matrix matrixSecondNew,
                                      final int countThreadsNew) {
        matrixFirst = matrixFirstNew;
        matrixSecond = matrixSecondNew;
        countThreads = countThreadsNew;
    }

    public Matrix multiplication() throws MatrixValidationException {
        if (MatrixValidation.isMultiplicableMatrices(matrixFirst,
                matrixSecond)) {
            if (countThreads > matrixFirst.getCountRows()) {
                countThreads = matrixFirst.getCountRows();
            }
            Matrix matrix = new Matrix(matrixFirst.getCountRows(),
                    matrixSecond.getCountColumns());
            int count = matrixFirst.getCountRows() / countThreads;
            int additional = matrixFirst.getCountRows() % countThreads;
            Thread[] threads = new Thread[countThreads];
            int start = 0;
            for (int i = 0; i < countThreads; i++) {
                int cnt = ((i == 0) ? count + additional : count);
                threads[i] = new Thread(new MultiplierThread(matrixFirst,
                        matrixSecond, matrix, start, start + cnt - 1));
                start += cnt;
            }
            startTread(threads);
            joinThread(threads);
            return matrix;
        } else {
            throw new MatrixValidationException();
        }
    }

    private void startTread(Thread[] threadsNew){
        for (Thread thread: threadsNew){
            thread.start();
        }
    }

    private void joinThread(Thread[] threadsNew) {
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
