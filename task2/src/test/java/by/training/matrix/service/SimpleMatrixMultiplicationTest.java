package by.training.matrix.service;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.exception.MatrixValidationException;
import by.training.matrix.service.multiplication.SimpleMatrixMultiplication;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SimpleMatrixMultiplicationTest {

    @Test(groups = {"Simple matrix multiplication group"},
            dataProvider = "multiplication positive")
    public void testMultiplicationPositive(Matrix matrixFirstNew,
                                           Matrix matrixSecondNew,
                                           Matrix matrixResult)
            throws MatrixValidationException {
        SimpleMatrixMultiplication simpleMatrixMultiplication =
                new SimpleMatrixMultiplication(matrixFirstNew, matrixSecondNew);
        assertEquals(simpleMatrixMultiplication.multiplication(), matrixResult);
    }

    @DataProvider(name = "multiplication positive")
    public Object[][] testMultiplicationPositiveProvider() {
        Matrix matrix = new Matrix(2, 2);
        matrix.setElement(0, 0, 1);
        matrix.setElement(0, 1, 2);
        matrix.setElement(1, 0, 3);
        matrix.setElement(1, 1, 4);
        Matrix matrix1 = new Matrix(2, 2);
        matrix1.setElement(0, 0, 1);
        matrix1.setElement(0, 1, 2);
        matrix1.setElement(1, 0, 3);
        matrix1.setElement(1, 1, 4);
        Matrix matrix2 = new Matrix(2, 2);
        matrix2.setElement(0, 0, 7);
        matrix2.setElement(0, 1, 10);
        matrix2.setElement(1, 0, 15);
        matrix2.setElement(1, 1, 22);

        Matrix matrix3 = new Matrix(2, 3);
        matrix3.setElement(0, 0, 1);
        matrix3.setElement(0, 1, 2);
        matrix3.setElement(0, 2, 5);
        matrix3.setElement(1, 0, 3);
        matrix3.setElement(1, 1, 4);
        matrix3.setElement(1, 2, 6);
        Matrix matrix4 = new Matrix(3, 2);
        matrix4.setElement(0, 0, 1);
        matrix4.setElement(0, 1, 2);
        matrix4.setElement(1, 0, 3);
        matrix4.setElement(1, 1, 4);
        matrix4.setElement(2, 0, 5);
        matrix4.setElement(2, 1, 6);
        Matrix matrix5 = new Matrix(2, 2);
        matrix5.setElement(0, 0, 32);
        matrix5.setElement(0, 1, 40);
        matrix5.setElement(1, 0, 45);
        matrix5.setElement(1, 1, 58);

        Matrix matrix6 = new Matrix(1);
        matrix6.setElement(0, 0, -1);
        Matrix matrix7 = new Matrix(1);
        matrix7.setElement(0, 0, 27);
        Matrix matrix8 = new Matrix(1);
        matrix8.setElement(0, 0, -27);
        return new Object[][]{
                {matrix, matrix1, matrix2},
                {matrix3, matrix4, matrix5},
                {matrix6, matrix7, matrix8}
        };
    }

    @Test(groups = {"Simple matrix multiplication group"},
            dataProvider = "multiplication negative",
            expectedExceptions = MatrixValidationException.class)
    public void testMultiplicationNegative(Matrix matrixFirstNew,
                                           Matrix matrixSecondNew,
                                           Matrix matrixResult)
            throws MatrixValidationException{
        SimpleMatrixMultiplication simpleMatrixMultiplication =
                new SimpleMatrixMultiplication(matrixFirstNew, matrixSecondNew);
        assertEquals(simpleMatrixMultiplication.multiplication(), matrixResult);
    }

    @DataProvider(name = "multiplication negative")
    public Object[][] testMultiplicationNegativeProvider() {
        Matrix matrix = new Matrix(2, 3);
        Matrix matrix1 = new Matrix(2, 2);
        Matrix matrix2 = new Matrix(2, 2);

        Matrix matrix3 = new Matrix(2, 3);
        Matrix matrix4 = new Matrix(1, 2);
        Matrix matrix5 = new Matrix(2, 2);

        Matrix matrix6 = new Matrix(1, 2);
        Matrix matrix7 = new Matrix(30, 1);
        Matrix matrix8 = new Matrix(1);
        return new Object[][]{
                {matrix, matrix1, matrix2},
                {matrix3, matrix4, matrix5},
                {matrix6, matrix7, matrix8}
        };
    }
}