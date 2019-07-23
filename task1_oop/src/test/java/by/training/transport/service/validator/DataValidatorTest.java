package by.training.transport.service.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DataValidatorTest {

    @Test(groups = {"Data validator group"},
            dataProvider = "is valid coupe positive")
    public void testIsValidCoupePositive(String[] stringsNew) {
        assertTrue(DataValidator.isValidCoupe(stringsNew));
    }

    @DataProvider(name = "is valid coupe positive")
    public Object[][] testIsValidCoupePositiveProvider() {
        return new Object[][]{{new String[]{"coupe", "1", "2", "3"}},
                {new String[]{"coupe", "0", "0", "0"}},
                {new String[]{"coupe", "36", "100", "110"}}};
    }

    @Test(groups = {"Data validator group"},
            dataProvider = "is valid coupe negative")
    public void testIsValidCoupeNegative(String[] stringsNew) {
        assertFalse(DataValidator.isValidCoupe(stringsNew));
    }

    @DataProvider(name = "is valid coupe negative")
    public Object[][] testIsValidCoupePositiveNegative() {
        return new Object[][]{{new String[]{"coupe", "-1", "2", "3"}},
                {new String[]{"coupe", "0", "-2", "100"}},
                {new String[]{"coupe", "37", "100", "110"}},
                {new String[]{"coupe", "1", "10", "-10"}}};
    }

    @Test(groups = {"Data validator group"},
            dataProvider = "is valid placecart positive")
    public void testIsValidPlacecartPositive(String[] stringsNew) {
        assertTrue(DataValidator.isValidPlacecart(stringsNew));
    }

    @DataProvider(name = "is valid placecart positive")
    public Object[][] testIsValidPlacecartPositiveProvider() {
        return new Object[][]{{new String[]{"placecart", "1", "2", "3"}},
                {new String[]{"placecart", "0", "0", "0"}},
                {new String[]{"placecart", "54", "100", "110"}}};
    }

    @Test(groups = {"Data validator group"},
            dataProvider = "is valid placecart negative")
    public void testIsValidPlacecartNegative(String[] stringsNew) {
        assertFalse(DataValidator.isValidPlacecart(stringsNew));
    }

    @DataProvider(name = "is valid placecart negative")
    public Object[][] testIsValidPlacecartNegativeProvider() {
        return new Object[][]{{new String[]{"placecart", "-1", "2", "3"}},
                {new String[]{"placecart", "0", "-2", "100"}},
                {new String[]{"placecart", "55", "100", "110"}},
                {new String[]{"placecart", "1", "10", "-10"}}};
    }

    @Test(groups = {"Data validator group"},
            dataProvider = "is valid seat carriage positive")
    public void testIsValidSeatCarriagePositive(String[] stringsNew) {
        assertTrue(DataValidator.isValidSeatCarriage(stringsNew));
    }

    @DataProvider(name = "is valid seat carriage positive")
    public Object[][] testIsValidSeatCarriagePositiveProvider() {
        return new Object[][]{{new String[]{"seatcarriage", "1", "2", "3"}},
                {new String[]{"seatcarriage", "0", "0", "0"}},
                {new String[]{"seatcarriage", "62", "100", "62"}}};
    }

    @Test(groups = {"Data validator group"},
            dataProvider = "is valid seat carriage negative")
    public void testIsValidSeatCarriageNegative(String[] stringsNew) {
        assertFalse(DataValidator.isValidSeatCarriage(stringsNew));
    }

    @DataProvider(name = "is valid seat carriage negative")
    public Object[][] testIsValidSeatCarriageNegativeProvider() {
        return new Object[][]{{new String[]{"seatcarriage", "-1", "2", "3"}},
                {new String[]{"seatcarriage", "0", "-2", "100"}},
                {new String[]{"seatcarriage", "63", "100", "13"}},
                {new String[]{"seatcarriage", "62", "100", "110"}},
                {new String[]{"seatcarriage", "1", "10", "-10"}}};
    }
}