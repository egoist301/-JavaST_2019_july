package by.training.transport.dao.factory;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.bean.entity.Placecart;
import by.training.transport.dao.factory.exception.CarriageValidationException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PlacecartFactoryTest {

    @Test(groups = {"Placecart factory group"},
            dataProvider = "create carriage positive")
    public void testCreateCarriagePositive(String[] stringsNew, PassengerCarriage passengerCarriageNew) throws CarriageValidationException {
        assertEquals(PlacecartFactory.getFactory().createCarriage(stringsNew), passengerCarriageNew);
    }

    @DataProvider(name = "create carriage positive")
    public Object[][] testCreateCarriagePositiveProvider() {
        return new Object[][]{{new String[]{"placecart", "15", "27", "8"}, new Placecart(15, 27, 8)},
                {new String[]{"placecart", "54", "2", "3"}, new Placecart(54, 2, 3)},
                {new String[]{"placecart", "0", "0", "0"}, new Placecart(0, 0, 0)}};
    }

    @Test(groups = {"Placecart factory group"},
            dataProvider = "create carriage negative",
            expectedExceptions = CarriageValidationException.class)
    public void testCreateCarriageNegative(String[] stringsNew, PassengerCarriage passengerCarriageNew) throws CarriageValidationException {
        assertEquals(PlacecartFactory.getFactory().createCarriage(stringsNew), passengerCarriageNew);
    }

    @DataProvider(name = "create carriage negative")
    public Object[][] testCreateCarriageNegativeProvider() {
        return new Object[][]{{new String[]{"placecart", "-15", "27", "8"}, new Placecart(15, 27, 8)},
                {new String[]{"placecart", "55", "2", "3"}, new Placecart(36, 2, 3)},
                {new String[]{"placecart", "0", "-2", "0"}, new Placecart(0, 0, 0)},
                {new String[]{"placecart", "15", "17", "-4"}, new Placecart(0, 0, 0)},
                {new String[]{"placecart", "-1", "-2", "-6"}, new Placecart(0, 0, 0)},
                {new String[]{"placecart", "-1", "-2", "6"}, new Placecart(0, 0, 0)},
                {new String[]{"placecart", "1", "-2", "-6"}, new Placecart(0, 0, 0)},
                {new String[]{"placecart", "-1", "2", "-6"}, new Placecart(0, 0, 0)}};
    }
}