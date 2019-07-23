package by.training.transport.dao.factory;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.bean.entity.SeatCarriage;
import by.training.transport.dao.factory.exception.CarriageValidationException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SeatCarriageFactoryTest {

    @Test(groups = {"Seat carriage factory group"},
            dataProvider = "create carriage positive")
    public void testCreateCarriagePositive(String[] stringsNew, PassengerCarriage passengerCarriageNew) throws CarriageValidationException {
        assertEquals(SeatCarriageFactory.getFactory().createCarriage(stringsNew), passengerCarriageNew);
    }

    @DataProvider(name = "create carriage positive")
    public Object[][] testCreateCarriagePositiveProvider() {
        return new Object[][]{{new String[]{"seatcarriage", "15", "27", "8"}, new SeatCarriage(15, 27, 8)},
                {new String[]{"seatcarriage", "62", "2", "3"}, new SeatCarriage(62, 2, 3)},
                {new String[]{"seatcarriage", "0", "0", "0"}, new SeatCarriage(0, 0, 0)}};
    }

    @Test(groups = {"Seat carriage factory group"},
            dataProvider = "create carriage negative",
            expectedExceptions = CarriageValidationException.class)
    public void testCreateCarriageNegative(String[] stringsNew, PassengerCarriage passengerCarriageNew) throws CarriageValidationException {
        assertEquals(SeatCarriageFactory.getFactory().createCarriage(stringsNew), passengerCarriageNew);
    }

    @DataProvider(name = "create carriage negative")
    public Object[][] testCreateCarriageNegativeProvider() {
        return new Object[][]{{new String[]{"seatcarriage", "-15", "27", "8"}, new SeatCarriage(15, 27, 8)},
                {new String[]{"seatcarriage", "63", "2", "3"}, new SeatCarriage(36, 2, 3)},
                {new String[]{"seatcarriage", "0", "-2", "0"}, new SeatCarriage(0, 0, 0)},
                {new String[]{"seatcarriage", "15", "17", "-4"}, new SeatCarriage(0, 0, 0)},
                {new String[]{"seatcarriage", "-1", "-2", "-6"}, new SeatCarriage(0, 0, 0)},
                {new String[]{"seatcarriage", "-1", "-2", "6"}, new SeatCarriage(0, 0, 0)},
                {new String[]{"seatcarriage", "1", "-2", "-6"}, new SeatCarriage(0, 0, 0)},
                {new String[]{"seatcarriage", "-1", "2", "-6"}, new SeatCarriage(0, 0, 0)}};
    }
}