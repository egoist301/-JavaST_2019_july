package by.training.matrix.bean;

/**
 * Matrix with synchronized method.
 */
public class MatrixWithSynchronized extends Matrix {
    /**
     * Default constructor.
     */
    public MatrixWithSynchronized() {
    }

    /**
     * Constructor.
     *
     * @param arrayNew two-dimensional array.
     */
    public MatrixWithSynchronized(final int[][] arrayNew) {
        super(arrayNew);
    }

    /**
     * Constructor.
     *
     * @param rows    rows of matrix.
     * @param columns columns of matrix.
     */
    public MatrixWithSynchronized(final int rows, final int columns) {
        super(rows, columns);
    }

    /**
     * Constructor.
     *
     * @param size square matrix size.
     */
    public MatrixWithSynchronized(final int size) {
        super(size);
    }

    /**
     * Set element of matrix.
     *
     * @param row     index of row.
     * @param column  index of column.
     * @param element element of matrix.
     */
    @Override
    public synchronized void setElement(final int row, final int column,
                                        final int element) {
        if (super.getElement(row, column) == 0) {
            super.setElement(row, column, element);
        }
    }
}
