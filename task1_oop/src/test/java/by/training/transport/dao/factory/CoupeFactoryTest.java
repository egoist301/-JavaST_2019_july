package by.training.transport.dao.factory;

import by.training.transport.bean.entity.Coupe;
import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.dao.factory.exception.CarriageValidationException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CoupeFactoryTest {

    @Test(groups = {"Coupe factory group"},
            dataProvider = "create carriage positive")
    public void testCreateCarriagePositive(String[] stringsNew, PassengerCarriage passengerCarriageNew) throws CarriageValidationException {
        assertEquals(CoupeFactory.getFactory().createCarriage(stringsNew), passengerCarriageNew);
    }

    @DataProvider(name = "create carriage positive")
    public Object[][] testCreateCarriagePositiveProvider() {
        return new Object[][]{{new String[]{"coupe", "15", "27", "8"}, new Coupe(15, 27, 8)},
                {new String[]{"coupe", "36", "2", "3"}, new Coupe(36, 2, 3)},
                {new String[]{"coupe", "0", "0", "0"}, new Coupe(0, 0, 0)}};
    }

    @Test(groups = {"Coupe factory group"},
            dataProvider = "create carriage negative",
            expectedExceptions = CarriageValidationException.class)
    public void testCreateCarriageNegative(String[] stringsNew, PassengerCarriage passengerCarriageNew) throws CarriageValidationException {
        assertEquals(CoupeFactory.getFactory().createCarriage(stringsNew), passengerCarriageNew);
    }

    @DataProvider(name = "create carriage negative")
    public Object[][] testCreateCarriageNegativeProvider() {
        return new Object[][]{{new String[]{"coupe", "-15", "27", "8"}, new Coupe(15, 27, 8)},
                {new String[]{"coupe", "37", "2", "3"}, new Coupe(36, 2, 3)},
                {new String[]{"coupe", "0", "-2", "0"}, new Coupe(0, 0, 0)},
                {new String[]{"coupe", "15", "17", "-4"}, new Coupe(0, 0, 0)},
                {new String[]{"coupe", "-1", "-2", "-6"}, new Coupe(0, 0, 0)},
                {new String[]{"coupe", "-1", "-2", "6"}, new Coupe(0, 0, 0)},
                {new String[]{"coupe", "1", "-2", "-6"}, new Coupe(0, 0, 0)},
                {new String[]{"coupe", "-1", "2", "-6"}, new Coupe(0, 0, 0)}};
    }
}