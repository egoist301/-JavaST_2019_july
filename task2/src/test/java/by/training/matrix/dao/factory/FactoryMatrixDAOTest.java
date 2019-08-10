package by.training.matrix.dao.factory;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.exception.MatrixValidationException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class FactoryMatrixDAOTest {

    @Test(groups = {"Factory matrix DAO"},
            dataProvider = "create matrix positive")
    public void testCreateMatrixPositive(List<String[]> listNew,
                                         Matrix matrixNew)
            throws MatrixValidationException {
        assertEquals(FactoryMatrixDAO.getFactory().createMatrix(listNew),
                matrixNew);
    }

    @DataProvider(name = "create matrix positive")
    public Object[][] testCreateMatrixPositiveProvider() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"1", "2", "3"});
        list.add(new String[]{"1", "2", "3"});
        Matrix matrix = new Matrix(2, 3);
        matrix.setElement(0, 0, 1);
        matrix.setElement(0, 1, 2);
        matrix.setElement(0, 2, 3);
        matrix.setElement(1, 0, 1);
        matrix.setElement(1, 1, 2);
        matrix.setElement(1, 2, 3);

        List<String[]> list1 = new ArrayList<>();
        list1.add(new String[]{"0"});
        Matrix matrix1 = new Matrix(1);

        List<String[]> list2 = new ArrayList<>();
        list2.add(new String[]{"0", "0", "0", "0", "0"});
        list2.add(new String[]{"0", "0", "0", "0", "0"});
        list2.add(new String[]{"0", "0", "0", "0", "0"});
        list2.add(new String[]{"0", "0", "0", "0", "0"});
        list2.add(new String[]{"0", "0", "0", "0", "0"});
        Matrix matrix2 = new Matrix();
        return new Object[][]{
                {list, matrix},
                {list1, matrix1},
                {list2, matrix2}
        };
    }

    @Test(groups = {"Factory matrix DAO"},
            dataProvider = "create matrix negative",
            expectedExceptions = MatrixValidationException.class)
    public void testCreateMatrixNegative(List<String[]> listNew,
                                         Matrix matrixNew)
            throws MatrixValidationException {
        assertEquals(FactoryMatrixDAO.getFactory().createMatrix(listNew),
                matrixNew);
    }

    @DataProvider(name = "create matrix negative")
    public Object[][] testCreateMatrixNegativeProvider() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"1", "2", "4"});
        list.add(new String[]{"1", "2", "3", "2"});
        Matrix matrix = new Matrix(2, 3);
        matrix.setElement(0, 0, 1);
        matrix.setElement(0, 1, 2);
        matrix.setElement(0, 2, 3);
        matrix.setElement(1, 0, 1);
        matrix.setElement(1, 1, 2);
        matrix.setElement(1, 2, 3);

        List<String[]> list1 = new ArrayList<>();
        list1.add(new String[]{"a"});
        Matrix matrix1 = new Matrix(1);

        List<String[]> list2 = new ArrayList<>();
        list2.add(new String[]{"0", "0", "0", "0", "0"});
        list2.add(new String[]{"0", "0", "0", "0", "0"});
        list2.add(new String[]{"a", "0", "0", "0", "0"});
        list2.add(new String[]{"0", "0", "0", "0", "0"});
        list2.add(new String[]{"0", "0", "0", "0", "0"});
        Matrix matrix2 = new Matrix();
        return new Object[][]{
                {list, matrix},
                {list1, matrix1},
                {list2, matrix2}
        };
    }
}
