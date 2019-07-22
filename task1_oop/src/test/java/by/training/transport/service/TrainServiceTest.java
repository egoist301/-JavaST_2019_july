package by.training.transport.service;

import by.training.transport.bean.entity.Coupe;
import by.training.transport.bean.entity.Placecart;
import by.training.transport.bean.entity.SeatCarriage;
import by.training.transport.dao.repository.Train;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TrainServiceTest {
    @Test(groups = {"Train service group"},
            dataProvider = "test counting number of passengers.")
    public void testCountingTheNumberOfPassengers(int actualPassengers,
                                                  int expectedPassengers) {
        assertEquals(actualPassengers, expectedPassengers);
    }

    @DataProvider(name = "test counting number of passengers.")
    public Object[][] testCountingTheNumberOfPassengersProvider() {
        Object[][] objects = new Object[3][2];
        Train train = Train.getTrain();
        objects[0][0] = TrainService.countingTheNumberOfPassengers(train);
        objects[0][1] = 0;
        train.add(new Coupe(3, 5, 3));
        train.add(new Placecart(2, 4, 3));
        objects[1][0] = TrainService.countingTheNumberOfPassengers(train);
        objects[1][1] = 5;
        train.add(new Coupe(4, 7, 3));
        train.add(new SeatCarriage(4, 6, 3));
        objects[2][0] = TrainService.countingTheNumberOfPassengers(train);
        objects[2][1] = 13;
        return objects;
    }

    @Test(groups = {"Train service group"},
            dataProvider = "test counting number of luggage.")
    public void testCountingTheNumberOfLuggage(int actualLuggage,
                                               int expectedLuggage) {
        assertEquals(actualLuggage, expectedLuggage);
    }

    @DataProvider(name = "test counting number of luggage.")
    public Object[][] testCountingTheNumberOfLuggageProvider() {
        Object[][] objects = new Object[3][2];
        Train train = Train.getTrain();
        objects[0][0] = TrainService.countingTheNumberOfLuggage(train);
        objects[0][1] = 0;
        train.add(new Coupe(3, 5, 3));
        train.add(new Placecart(2, 4, 3));
        objects[1][0] = TrainService.countingTheNumberOfLuggage(train);
        objects[1][1] = 9;
        train.add(new Coupe(4, 7, 3));
        train.add(new SeatCarriage(4, 6, 3));
        objects[2][0] = TrainService.countingTheNumberOfLuggage(train);
        objects[2][1] = 22;
        return objects;
    }
}