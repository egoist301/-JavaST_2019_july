package by.training.transport.dao.repository;

import by.training.transport.bean.entity.Coupe;
import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.bean.entity.Placecart;
import by.training.transport.bean.entity.SeatCarriage;
import by.training.transport.dao.repository.exception.IllegalSpecificationException;
import by.training.transport.dao.repository.specification.findspecification.FindSpecificationById;
import by.training.transport.dao.repository.specification.findspecification.FindSpecificationByLuggage;
import by.training.transport.dao.repository.specification.findspecification.FindSpecificationByPassengers;
import by.training.transport.dao.repository.specification.findspecification.FindSpecificationByPassengersRange;
import by.training.transport.dao.repository.specification.sortspecification.SortSpecificationByLuggage;
import by.training.transport.dao.repository.specification.sortspecification.SortSpecificationByPassengers;
import by.training.transport.dao.repository.specification.sortspecification.SortSpecificationByPassengersThenByLuggage;
import by.training.transport.service.TrainService;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class TrainTest {
    @AfterMethod(groups = {"clear group"})
    public void clearTrain() {
        Train.getTrain().clear();
    }

    @Test(groups = {"Train group", " clear group"},
            dataProvider = "test get number of passengers")
    public void testGetNumberOfPassengers(int actual, int expected) {
        assertEquals(actual, expected);
    }

    @DataProvider(name = "test get number of passengers")
    public Object[][] testGetNumberOfPassengersProvider() {
        Train train = Train.getTrain();
        Object[][] objects = new Object[3][2];
        int firstTest = train.getNumberOfPassengers();
        train.add(new Coupe(1, 2, 3));
        int secondTest = train.getNumberOfPassengers();
        train.add(new Placecart(2, 3, 1));
        int thirdTest = train.getNumberOfPassengers();
        objects[0][0] = firstTest;
        objects[0][1] = 0;
        objects[1][0] = secondTest;
        objects[1][1] = 1;
        objects[2][0] = thirdTest;
        objects[2][1] = 3;
        return objects;
    }

    @Test(groups = {"Train group", " clear group"},
            dataProvider = "test get number of luggage")
    public void testGetNumberOLuggage(int actual, int expected) {
        assertEquals(actual, expected);
    }

    @DataProvider(name = "test get number of luggage")
    public Object[][] testGetNumberOfLuggageProvider() {
        Train train = Train.getTrain();
        Object[][] objects = new Object[3][2];
        int firstTest = train.getNumberOfLuggage();
        System.out.println(firstTest);
        train.add(new Coupe(1, 2, 3));
        int secondTest = train.getNumberOfLuggage();
        train.add(new Placecart(2, 3, 1));
        int thirdTest = train.getNumberOfLuggage();
        objects[0][0] = firstTest;
        objects[0][1] = 0;
        objects[1][0] = secondTest;
        objects[1][1] = 2;
        objects[2][0] = thirdTest;
        objects[2][1] = 5;
        return objects;
    }

    @Test(groups = {"Train group", " clear group"})
    public void testAdd() {
        Train train = Train.getTrain();
        train.add(new Coupe(1, 2, 2));
        train.add(new Placecart(1, 2, 3));
        assertEquals(train.size(), 2);

    }

    @Test(groups = {"Train group", " clear group"})
    public void testAddEmpty() {
        Train train = Train.getTrain();
        assertEquals(train.size(), 0);
    }

    @Test(groups = {"Train group", " clear group"},
            dataProvider = "sort specification")
    public void testQuerySort(List<PassengerCarriage> actualList,
                              List<PassengerCarriage> expectedList) {
        assertEquals(actualList, expectedList);
    }

    @DataProvider(name = "sort specification")
    public Object[][] testQuerySortProvider()
            throws IllegalSpecificationException {
        Train train = Train.getTrain();
        train.add(new Coupe(3, 5, 3));
        train.add(new Placecart(2, 4, 3));
        train.add(new Coupe(4, 7, 3));
        train.add(new SeatCarriage(4, 6, 3));
        List<PassengerCarriage> actualListSortByPassengers =
                new ArrayList<>(train.query(new SortSpecificationByPassengers()));
        List<PassengerCarriage> actualListSortByLuggage =
                new ArrayList<>(train.query(new SortSpecificationByLuggage()));
        List<PassengerCarriage> actualListSortByPassengersThenLuggage =
                new ArrayList<>(train.query(new SortSpecificationByPassengersThenByLuggage()));
        List<PassengerCarriage> expectedListSortByPassengers =
                new ArrayList<>();
        expectedListSortByPassengers.add(new Placecart(2, 4, 3));
        expectedListSortByPassengers.add(new Coupe(3, 5, 3));
        expectedListSortByPassengers.add(new Coupe(4, 7, 3));
        expectedListSortByPassengers.add(new SeatCarriage(4, 6, 3));
        List<PassengerCarriage> expectedListSortByLuggage = new ArrayList<>();
        expectedListSortByLuggage.add(new Placecart(2, 4, 3));
        expectedListSortByLuggage.add(new Coupe(3, 5, 3));
        expectedListSortByLuggage.add(new SeatCarriage(4, 6, 3));
        expectedListSortByLuggage.add(new Coupe(4, 7, 3));
        List<PassengerCarriage> expectedListSortByPassengersThenLuggage =
                new ArrayList<>();
        expectedListSortByPassengersThenLuggage.add(new Placecart(2, 4, 3));
        expectedListSortByPassengersThenLuggage.add(new Coupe(3, 5, 3));
        expectedListSortByPassengersThenLuggage.add(new SeatCarriage(4, 6, 3));
        expectedListSortByPassengersThenLuggage.add(new Coupe(4, 7, 3));
        return new Object[][]{
                {actualListSortByLuggage, expectedListSortByLuggage},
                {actualListSortByPassengers, expectedListSortByPassengers},
                {actualListSortByPassengersThenLuggage,
                        expectedListSortByPassengersThenLuggage}};
    }

    @Test(groups = {"Train group", " clear group"},
            dataProvider = "find specification")
    public void testQueryFind(List<PassengerCarriage> actualList,
                              List<PassengerCarriage> expectedList) {
        assertEquals(actualList, expectedList);
    }

    @DataProvider(name = "find specification")
    public Object[][] testQueryFindProvider()
            throws IllegalSpecificationException {
        Train train = Train.getTrain();
        train.add(new Coupe(30, 21, 15));
        train.add(new Placecart(15, 40, 21));
        train.add(new SeatCarriage(14, 38, 46));
        train.add(new Coupe(8, 14, 23));
        train.add(new Placecart(39, 42, 55));
        train.add(new SeatCarriage(17, 60, 59));
        train.add(new Coupe(31, 12, 18));
        train.add(new Placecart(17, 20, 19));
        train.add(new SeatCarriage(28, 34, 10));
        List<PassengerCarriage> actualListFindById =
                new ArrayList<>(train.query(new FindSpecificationById(19)));
        List<PassengerCarriage> actualListFindByPassengers =
                new ArrayList<>(train.query(new FindSpecificationByPassengers(17)));
        List<PassengerCarriage> actualListFindByIdEmpty =
                new ArrayList<>(train.query(new FindSpecificationById(100)));
        List<PassengerCarriage> actualListFindByLuggage =
                new ArrayList<>(train.query(new FindSpecificationByLuggage(42)));
        List<PassengerCarriage> actualListFindByRangePassengers =
                new ArrayList<>(train.query(new FindSpecificationByPassengersRange(13, 16)));
        List<PassengerCarriage> actualListFindByRangePassengersEmpty =
                new ArrayList<>(train.query(new FindSpecificationByPassengersRange(0, 1)));
        List<PassengerCarriage> expectedListFindById = new ArrayList<>();
        expectedListFindById.add(new SeatCarriage(14, 38, 46));
        List<PassengerCarriage> expectedListFindByPassengers =
                new ArrayList<>();
        expectedListFindByPassengers.add(new SeatCarriage(17, 60, 59));
        expectedListFindByPassengers.add(new Placecart(17, 20, 19));
        List<PassengerCarriage> expectedListFindByIdEmpty = new ArrayList<>();
        List<PassengerCarriage> expectedListFindByLuggage = new ArrayList<>();
        expectedListFindByLuggage.add(new Placecart(39, 42, 55));
        List<PassengerCarriage> expectedListFindByRangePassengers =
                new ArrayList<>();
        expectedListFindByRangePassengers.add(new Placecart(15, 40, 21));
        expectedListFindByRangePassengers.add(new SeatCarriage(14, 38, 46));
        List<PassengerCarriage> expectedListFindByRangePassengersEmpty =
                new ArrayList<>();
        return new Object[][]{{actualListFindById, expectedListFindById},
                {actualListFindByIdEmpty, expectedListFindByIdEmpty},
                {actualListFindByLuggage, expectedListFindByLuggage},
                {actualListFindByPassengers, expectedListFindByPassengers},
                {actualListFindByRangePassengers,
                        expectedListFindByRangePassengers},
                {actualListFindByRangePassengersEmpty,
                        expectedListFindByRangePassengersEmpty}};
    }

    @Test(groups = {"Train group", " clear group"})
    public void testSize() {
        Train train = Train.getTrain();
        train.add(new Coupe(1, 2, 2));
        train.add(new Placecart(1, 2, 3));
        assertEquals(train.size(), 2);
    }

    @Test(groups = {"Train group", " clear group"})
    public void testSizeEmpty() {
        Train train = Train.getTrain();
        assertEquals(train.size(), 0);
    }

    @Test(groups = {"Train group", " clear group"})
    public void testGet() {
        Train train = Train.getTrain();
        train.add(new Coupe(1, 2, 3));
        train.add(new Placecart(1, 2, 3));
        train.add(new SeatCarriage(1, 2, 3));
        assertEquals(new Placecart(1, 2, 3), train.get(1));
    }

    @Test(groups = {"Train group", " clear group"})
    public void testSet() {
        Train train = Train.getTrain();
        train.add(new Coupe(1, 2, 3));
        train.add(new Placecart(1, 2, 3));
        train.add(new SeatCarriage(1, 2, 3));
        train.set(new SeatCarriage(3, 2, 1), 1);
        assertEquals(new SeatCarriage(3, 2, 1), train.get(1));
    }

    @Test(groups = {"Train group", " clear group"},
            dataProvider = "test get all")
    public void testGetAll(List<PassengerCarriage> trainNew,
                           List<PassengerCarriage> passengerCarriageList) {
        assertEquals(trainNew, passengerCarriageList);
    }

    @DataProvider(name = "test get all")
    public Object[][] testGetAllProvider() {
        Object[][] objects = new Object[2][2];
        Train train = Train.getTrain();
        objects[0][0] = new ArrayList<>(train.getAll());
        objects[0][1] = new ArrayList<PassengerCarriage>();
        train.add(new Coupe(1, 2, 3));
        train.add(new Placecart(1, 2, 3));
        train.add(new SeatCarriage(1, 2, 3));
        List<PassengerCarriage> passengerCarriageList = new ArrayList<>();
        passengerCarriageList.add(new Coupe(1, 2, 3));
        passengerCarriageList.add(new Placecart(1, 2, 3));
        passengerCarriageList.add(new SeatCarriage(1, 2, 3));
        objects[1][0] = new ArrayList<>(train.getAll());
        objects[1][1] = passengerCarriageList;
        return objects;
    }
}