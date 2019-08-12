package by.training.matrix.service.diagonal;

import by.training.matrix.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Diagonal initializer with executor.
 */
public class DiagonalInitializerWithExecutor implements DiagonalInitializable {
    /**
     * Matrix.
     */
    private Matrix matrix;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Constructor.
     *
     * @param matrixNew matrix.
     */
    public DiagonalInitializerWithExecutor(final Matrix matrixNew) {
        matrix = matrixNew;
    }

    /**
     * Initialize diagonal.
     */
    @Override
    public void initializeDiagonal() {
        if (matrix == null) {
            throw new IllegalArgumentException(
                    "Arguments can't be null.");
        }
        ExecutorService executor = Executors.
                newFixedThreadPool(matrix.getCountRows());
        List<Lock> locks = new ArrayList<>();
        initializeLocks(locks);
        for (int i = 0; i < matrix.getCountColumns(); i++) {
            executor.execute(new DiagonalTaskWithExecutor(i + 2,
                    matrix, locks));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            LOGGER.warn("error during sleeping", e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Initializes the list with locks.
     *
     * @param locksNew locks.
     */
    private void initializeLocks(final List<Lock> locksNew) {
        for (int i = 0; i < matrix.getCountColumns(); i++) {
            locksNew.add(new ReentrantLock());
        }
    }
}
