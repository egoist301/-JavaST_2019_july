package by.training.matrix.service;

import by.training.matrix.bean.Matrix;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Matrix validation test.
 */
public class MatrixValidationTest {

    /**
     * Testing check for multiplication of two matrices.
     * Positive way.
     *
     * @param matrixFirstNew  first matrix.
     * @param matrixSecondNew second matrix.
     */
    @Test(groups = {"Matrix validation group"},
            dataProvider = "is multiplicable matrices positive")
    public void testIsMultiplicableMatricesPositive(final Matrix matrixFirstNew,
                                                    final
                                                    Matrix matrixSecondNew) {
        assertTrue(MatrixValidation.isMultiplicableMatrices(matrixFirstNew,
                matrixSecondNew));
    }

    /**
     * Provider.
     *
     * @return first matrix and second matrix for multiplication.
     */
    @DataProvider(name = "is multiplicable matrices positive")
    public Object[][] testIsMultiplicableMatricesPositiveProvider() {
        Matrix matrix = new Matrix(1, 2);
        Matrix matrix1 = new Matrix(2, 3);
        Matrix matrix2 = new Matrix(3, 3);
        Matrix matrix3 = new Matrix(2, 5);
        return new Object[][]{
                {matrix, matrix1},
                {matrix, matrix3},
                {matrix1, matrix2}
        };
    }

    /**
     * Testing check for multiplication of two matrices.
     * Negative way.
     *
     * @param matrixFirstNew  first matrix.
     * @param matrixSecondNew second matrix.
     */
    @Test(groups = {"Matrix validation group"},
            dataProvider = "is multiplicable matrices negative")
    public void testIsMultiplicableMatricesNegative(final Matrix matrixFirstNew,
                                                    final
                                                    Matrix matrixSecondNew) {
        assertFalse(MatrixValidation.isMultiplicableMatrices(matrixFirstNew,
                matrixSecondNew));
    }

    /**
     * Provider.
     *
     * @return first matrix and second matrix for multiplication.
     */
    @DataProvider(name = "is multiplicable matrices negative")
    public Object[][] testIsMultiplicableMatricesNegativeProvider() {
        Matrix matrix = new Matrix(1, 2);
        Matrix matrix1 = new Matrix(2, 3);
        Matrix matrix2 = new Matrix(3, 3);
        Matrix matrix3 = new Matrix(2, 5);
        return new Object[][]{
                {matrix1, matrix},
                {matrix, matrix2},
                {matrix1, matrix3},
                {matrix3, matrix1},
                {matrix2, matrix}
        };
    }

    /**
     * Testing check integer matrix.
     * Positive way.
     *
     * @param listNew list of arrays of strings.
     */
    @Test(groups = {"Matrix validation group"},
            dataProvider = "is integer matrix positive")
    public void testIsIntegerMatrixPositive(final List<String[]> listNew) {
        assertTrue(MatrixValidation.isIntegerMatrix(listNew));
    }

    /**
     * Provider.
     *
     * @return list of arrays of strings.
     */
    @DataProvider(name = "is integer matrix positive")
    public Object[][] testIsIntegerMatrixPositiveProvider() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"1", "2", "3"});
        list.add(new String[]{"1", "2", "3"});
        list.add(new String[]{"1", "2", "3"});
        list.add(new String[]{"1", "2", "3"});
        list.add(new String[]{"1", "2", "3"});
        List<String[]> list1 = new ArrayList<>();
        list1.add(new String[]{"1", "2", "3", "4", "5", "6"});
        return new Object[][]{
                {list},
                {list1}
        };
    }

    /**
     * Testing check integer matrix.
     * Positive way.
     *
     * @param listNew list of arrays of strings.
     */
    @Test(groups = {"Matrix validation group"},
            dataProvider = "is integer matrix negative")
    public void testIsIntegerMatrixNegative(final List<String[]> listNew) {
        assertFalse(MatrixValidation.isIntegerMatrix(listNew));
    }

    /**
     * Provider.
     *
     * @return list of arrays of strings.
     */
    @DataProvider(name = "is integer matrix negative")
    public Object[][] testIsIntegerMatrixNegativeProvider() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"1", "2", "3"});
        list.add(new String[]{"1", "2", "3"});
        list.add(new String[]{"1", "2", "n"});
        list.add(new String[]{"1", "2", "3"});
        list.add(new String[]{"1", "2", "3"});
        List<String[]> list1 = new ArrayList<>();
        list1.add(new String[]{"1", "2", "g", "d", "5", "6"});
        List<String[]> list2 = new ArrayList<>();
        list2.add(new String[]{"daw", "dwa", "dwa"});
        List<String[]> list3 = new ArrayList<>();
        list3.add(new String[]{"21ds", "221da"});
        return new Object[][]{
                {list},
                {list1},
                {list2},
                {list3}
        };
    }

    /**
     * Testing check square matrix.
     * Positive way.
     *
     * @param matrixNew matrix.
     */
    @Test(groups = {"Matrix validation group"},
            dataProvider = "is square matrix positive")
    public void testIsSquareMatrixPositive(final Matrix matrixNew) {
        assertTrue(MatrixValidation.isSquareMatrix(matrixNew));
    }

    /**
     * Provider.
     *
     * @return matrix.
     */
    @DataProvider(name = "is square matrix positive")
    public Object[][] testIsSquareMatrixPositiveProvider() {
        return new Object[][]{
                {new Matrix()},
                {new Matrix(1)},
                {new Matrix(100)},
                {new Matrix(12)}
        };
    }

    /**
     * Testing check square matrix.
     * Positive way.
     *
     * @param matrixNew matrix.
     */
    @Test(groups = {"Matrix validation group"},
            dataProvider = "is square matrix negative")
    public void testIsSquareMatrixNegative(final Matrix matrixNew) {
        assertFalse(MatrixValidation.isSquareMatrix(matrixNew));
    }

    /**
     * Provider.
     *
     * @return matrix.
     */
    @DataProvider(name = "is square matrix negative")
    public Object[][] testIsSquareMatrixNegativeProvider() {
        return new Object[][]{
                {new Matrix(1, 3)},
                {new Matrix(3, 1)},
                {new Matrix(100, 120)},
                {new Matrix(12, 13)}
        };
    }
}
