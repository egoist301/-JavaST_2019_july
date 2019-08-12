package by.training.matrix.bean;

import java.util.Arrays;

/**
 * Matrix.
 */
public class Matrix {
    /**
     * Default size.
     */
    private static final int DEFAULT_SIZE = 5;
    /**
     * Matrix.
     */
    private int[][] array;

    /**
     * Default constructor.
     */
    public Matrix() {
        array = new int[DEFAULT_SIZE][DEFAULT_SIZE];
    }

    /**
     * Constructor.
     *
     * @param arrayNew two-dimensional array.
     */
    public Matrix(final int[][] arrayNew) {
        array = new int[arrayNew.length][arrayNew[0].length];
        int size = arrayNew.length;
        for (int i = 0; i < size; ++i) {
            System.arraycopy(array[i], 0, arrayNew[i], 0, size);
        }
    }

    /**
     * Constructor.
     *
     * @param rows    rows of matrix.
     * @param columns columns of matrix.
     */
    public Matrix(final int rows, final int columns) {
        array = new int[rows][columns];
    }

    /**
     * Constructor.
     *
     * @param size square matrix size.
     */
    public Matrix(final int size) {
        this(size, size);
    }

    /**
     * Copy constructor.
     *
     * @param matrix matrix.
     */
    public Matrix(final Matrix matrix) {
        this(matrix.array.length, matrix.array[0].length);
        int size = matrix.array.length;
        for (int i = 0; i < size; i++) {
            System.arraycopy(array[i], 0, matrix.array[i], 0, size);
        }
    }

    /**
     * Getter.
     *
     * @return count rows of matrix.
     */
    public int getCountRows() {
        return array.length;
    }

    /**
     * Getter.
     *
     * @return count columns of matrix.
     */
    public int getCountColumns() {
        return array[0].length;
    }

    /**
     * Get element by id.
     *
     * @param row    index of row.
     * @param column index of column.
     * @return element of matrix.
     */
    public int getElement(final int row, final int column) {
        return array[row][column];
    }

    /**
     * Set element by id.
     *
     * @param row     index of row.
     * @param column  index of column.
     * @param element element of matrix.
     */
    public void setElement(final int row, final int column, final int element) {
        array[row][column] = element;
    }

    /**
     * Equal objects.
     *
     * @param oNew object.
     * @return equal or unequal.
     */
    @Override
    public boolean equals(final Object oNew) {
        if (this == oNew) {
            return true;
        }
        if (!(oNew instanceof Matrix)) {
            return false;
        }
        Matrix matrix = (Matrix) oNew;
        return Arrays.deepEquals(array, matrix.array);
    }

    /**
     * Hashcode.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    /**
     * String representation of an object.
     *
     * @return matrix.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int[] mas : array) {
            for (int elem : mas) {
                builder.append(elem).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
