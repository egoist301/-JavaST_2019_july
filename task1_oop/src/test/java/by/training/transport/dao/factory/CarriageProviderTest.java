package by.training.transport.dao.factory;

import by.training.transport.bean.entity.Placecart;
import by.training.transport.dao.factory.exception.CarriageValidationException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CarriageProviderTest {

    @Test(groups = {"Carriage provider group"},
    dataProvider = "select carriage factory.")
    public void testSelectCarriageFactory(String actual, CarriageFactory expected) throws CarriageValidationException {
        assertEquals(new CarriageProvider().selectCarriageFactory(actual), expected);
    }
    @DataProvider(name = "select carriage factory.")
    public Object[][] testSelectCarriageFactoryProvider(){
        return new Object[][]{{"coupe", CoupeFactory.getFactory()},
                {"COupe", CoupeFactory.getFactory()},
                {"COUPE", CoupeFactory.getFactory()},
                {"Placecart", PlacecartFactory.getFactory()},
                {"SeatCarriage", SeatCarriageFactory.getFactory()}};
    }

    @Test(groups = {"Carriage provider group"},
            expectedExceptions = CarriageValidationException.class)
    public void testSelectCarriageFactoryException() throws  CarriageValidationException{
        assertEquals(new CarriageProvider().selectCarriageFactory("sdadwa"), CoupeFactory.getFactory());
    }
}