package by.training.matrix.service;

import by.training.matrix.bean.Matrix;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Matrix initializer test.
 */
public class MatrixInitializerTest {

    /**
     * Testing initialize matrix some values.
     *
     * @param actual actual matrix.
     * @param result result matrix.
     */
    @Test(groups = {"Matrix initializer group"},
            dataProvider = "initialize")
    public void testInitialize(final Matrix actual, final Matrix result) {
        MatrixInitializer.initialize(actual);
        assertEquals(actual, result);
    }

    /**
     * Provider.
     *
     * @return actual matrix and result matrix.
     */
    @DataProvider(name = "initialize")
    public Object[][] testInitializeProvider() {
        Matrix matrix = new Matrix(1);
        Matrix result = new Matrix(1);
        result.setElement(0, 0, 2);

        Matrix matrix1 = new Matrix(2);
        Matrix result1 = new Matrix(2);
        result1.setElement(0, 0, 2);
        result1.setElement(0, 1, 2);
        result1.setElement(1, 0, 3);
        result1.setElement(1, 1, 3);

        Matrix matrix2 = new Matrix();
        Matrix result2 = new Matrix();

        result2.setElement(0, 0, 2);
        result2.setElement(0, 1, 2);
        result2.setElement(0, 2, 2);
        result2.setElement(0, 3, 2);
        result2.setElement(0, 4, 2);

        result2.setElement(1, 0, 3);
        result2.setElement(1, 1, 3);
        result2.setElement(1, 2, 3);
        result2.setElement(1, 3, 3);
        result2.setElement(1, 4, 3);

        result2.setElement(2, 0, 4);
        result2.setElement(2, 1, 4);
        result2.setElement(2, 2, 4);
        result2.setElement(2, 3, 4);
        result2.setElement(2, 4, 4);

        result2.setElement(3, 0, 5);
        result2.setElement(3, 1, 5);
        result2.setElement(3, 2, 5);
        result2.setElement(3, 3, 5);
        result2.setElement(3, 4, 5);

        result2.setElement(4, 0, 6);
        result2.setElement(4, 1, 6);
        result2.setElement(4, 2, 6);
        result2.setElement(4, 3, 6);
        result2.setElement(4, 4, 6);

        return new Object[][]{
                {matrix, result},
                {matrix1, result1},
                {matrix2, result2}
        };
    }

    /**
     * Testing initialize diagonal zero.
     *
     * @param actual actual matrix.
     * @param result result matrix.
     */
    @Test(groups = {"Matrix initializer group"},
            dataProvider = "diagonal zero")
    public void testInitializeDiagonalZero(final Matrix actual,
                                           final Matrix result) {
        MatrixInitializer.initializeDiagonalZero(actual);
        assertEquals(actual, result);
    }

    /**
     * Provider.
     *
     * @return actual matrix and result matrix.
     */
    @DataProvider(name = "diagonal zero")
    public Object[][] testInitializeDiagonalZeroProvider() {
        Matrix matrix = new Matrix(1);
        matrix.setElement(0, 0, 1);
        Matrix result = new Matrix(1);

        Matrix matrix1 = new Matrix(2);
        matrix1.setElement(0, 0, 1);
        matrix1.setElement(0, 1, 2);
        matrix1.setElement(1, 1, 3);
        Matrix result1 = new Matrix(2);
        result1.setElement(0, 1, 2);

        Matrix matrix2 = new Matrix();
        matrix2.setElement(2, 2, 2);
        Matrix result2 = new Matrix();
        return new Object[][]{
                {matrix, result},
                {matrix1, result1},
                {matrix2, result2}
        };
    }
}
