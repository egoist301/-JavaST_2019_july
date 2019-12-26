package by.training.catalog.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PaginationValidatorTest {
    @DataProvider(name = "incorrect page")
    private Object[][] invalidPageProvider() {
        return new Object[][]{
                {-10},
                {0}
        };
    }

    @DataProvider(name = "correct limit")
    private Object[][] validLimitProvider() {
        return new Object[][]{
                {10},
                {0}
        };
    }

    @Test
    public void testIsValidPage() {
        int actualPage = 4;
        boolean result = PaginationValidator.isValidPage(actualPage);
        assertTrue(result);
    }

    @Test(dataProvider = "incorrect page")
    public void testIsInvalidPage(int actualPage) {
        boolean result = PaginationValidator.isValidPage(actualPage);
        assertFalse(result);
    }

    @Test
    public void testIsInvalidLimit() {
        int actualLimit = -4;
        boolean result = PaginationValidator.isValidLimit(actualLimit);
        assertFalse(result);
    }

    @Test(dataProvider = "correct limit")
    public void testIsValidLimit(int actualLimit) {
        boolean result = PaginationValidator.isValidLimit(actualLimit);
        assertTrue(result);
    }
}