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
     * @param arrayNew two-dimensional array.
     */
    public Matrix(final int[][] arrayNew) {
        array = new int[arrayNew.length][arrayNew[0].length];
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[0].length; ++j) {
                array[i][j] = arrayNew[i][j];
            }
        }
    }

    /**
     * @param rows    rows of matrix.
     * @param columns columns of matrix.
     */
    public Matrix(final int rows, final int columns) {
        array = new int[rows][columns];
    }

    /**
     * @param size square matrix size.
     */
    public Matrix(final int size) {
        this(size, size);
    }

    /**
     * @param matrix copy constructor.
     */
    public Matrix(final Matrix matrix) {
        this(matrix.array.length, matrix.array[0].length);
        for (int i = 0; i < matrix.array.length; i++) {
            for (int j = 0; j < matrix.array[i].length; j++) {
                array[i][j] = matrix.array[i][j];
            }
        }
    }

    /**
     * @return count rows of matrix.
     */
    public int getCountRows() {
        return array.length;
    }

    /**
     * @return count columns of matrix.
     */
    public int getCountColumns() {
        return array[0].length;
    }

    /**
     * @param row    index of row.
     * @param column index of column.
     * @return element of matrix.
     */
    public int getElement(final int row, final int column) {
        return array[row][column];
    }

    /**
     * @param row     index of row.
     * @param column  index of column.
     * @param element element of matrix.
     */
    public void setElement(final int row, final int column, final int element) {
        array[row][column] = element;
    }

    /**
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
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    /**
     * @return matrix.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                builder.append(array[i][j]).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
