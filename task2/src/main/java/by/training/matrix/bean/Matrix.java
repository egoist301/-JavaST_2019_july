package by.training.matrix.bean;

public class Matrix {
    private static final int DEFAULT_SIZE = 5;
    private int[][] array;

    public Matrix() {
        array = new int[DEFAULT_SIZE][DEFAULT_SIZE];
    }

    public Matrix(final int[][] arrayNew) {
        array = new int[arrayNew.length][arrayNew[0].length];
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[0].length; ++j) {
                array[i][j] = arrayNew[i][j];
            }
        }
    }

    public Matrix(final int rows, final int columns) {
        array = new int[rows][columns];
    }

    public Matrix(final int size) {
        this(size, size);
    }

    public Matrix(final Matrix matrix) {
        this(matrix.array.length, matrix.array[0].length);
        for (int i = 0; i < matrix.array.length; i++) {
            for (int j = 0; j < matrix.array[i].length; j++) {
                array[i][j] = matrix.array[i][j];
            }
        }
    }

    public int getCountRows() {
        return array.length;
    }

    public int getCountColumns() {
        return array[0].length;
    }

    public double getElement(final int i, final int j) {
        return array[i][j];
    }

    public void setElement(final int i, final int j, final int element) {
        array[i][j] = element;
    }

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
