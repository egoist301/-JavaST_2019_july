package by.training.catalog.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PaginationTest {
    @DataProvider(name = "correct page and limit")
    private Object[][] correctPageAndLimitProvider() {
        return new Object[][]{
                {1, 2, 0},
                {2, 2, 2}
        };
    }

    @DataProvider(name = "incorrect page and limit with exception")
    private Object[][] incorrectPageAndLimitWithExceptionProvider() {
        return new Object[][]{
                {0, 4, 1,},
                {1, -1, 2},
                {-1, -1, 3}
        };
    }

    @Test(dataProvider = "correct page and limit")
    public void testCalcOffsetCorrect(int actualPage,
                                      int actualLimit, int expectedOffset)
            throws ServiceException {
        int actualOffset = Pagination.calcOffset(actualPage, actualLimit);
        assertEquals(actualOffset, expectedOffset);
    }

    @Test(dataProvider = "incorrect page and limit with exception",
            expectedExceptions = ServiceException.class)
    public void testCalcOffsetWithException(int actualPage,
                                            int actualLimit,
                                            int expectedOffset)
            throws ServiceException {
        int actualOffset = Pagination.calcOffset(actualPage, actualLimit);
        assertEquals(actualOffset, expectedOffset);
    }
}