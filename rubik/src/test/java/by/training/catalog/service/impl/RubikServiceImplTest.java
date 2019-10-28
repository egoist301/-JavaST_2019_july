package by.training.catalog.service.impl;

import by.training.catalog.bean.RawData;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import com.wix.mysql.SqlScriptSource;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.wix.mysql.ScriptResolver.classPathScript;
import static org.testng.Assert.*;

public class RubikServiceImplTest extends AbstractServiceTest {
    private RubikService service;
    private static final SqlScriptSource[] SQL_SCRIPT_SOURCES
            = {classPathScript("sql/fill_user_table.sql")};

    @BeforeTest
    public void setUp() {
        ServiceFactory serviceFactory = new ServiceFactory();
        service = serviceFactory.createRubikService();
        database.executeScripts(DATABASE, SQL_SCRIPT_SOURCES);
    }

    @DataProvider(name = "Correct find all")
    private Object[][] findAllCorrectProvider() {
        return new Object[][]{
                {0, 5, 5},
                {1, 3, 3},
                {3, 2, 2},
                {5, 1, 1},
                {1, 0, 0}
        };
    }

    @DataProvider(name = "Incorrect find all")
    private Object[][] findAllIncorrectProvider() {
        return new Object[][]{
                {-1, 3, 3},
                {0, -1, 2}
        };
    }

    @DataProvider(name = "Correct find rubik's by model")
    private Object[][] findRubiksByModelCorrectProvider() {
        return new Object[][]{
                {"", 0, 12, 11},
                {"x", 1, 2, 2},
                {"The Valk 3", 0, 2, 1},
                {"dawfeawgawgaw", 1, 1, 0}
        };
    }

    @DataProvider(name = "Incorrect find rubik's by model")
    private Object[][] findRubiksByModelIncorrectProvider() {
        return new Object[][]{
                {"", -1, 1, 1},
                {"", 1, -1, 1}
        };
    }

    @DataProvider(name = "Correct find rubik's by manufacturer")
    private Object[][] findRubiksByManufacturerCorrectProvider() {
        return new Object[][]{
                {"", 12, 0, 11},
                {"y", 2, 0, 2},
                {"moffange", 2, 0, 2},
                {"dawfeawgawgaw", 1, 1, 0}
        };
    }

    @DataProvider(name = "Incorrect find rubik's by manufacturer")
    private Object[][] findRubiksByManufacturerIncorrectProvider() {
        return new Object[][]{
                {"", -1, 1, 1},
                {"", 1, -1, 1}
        };
    }

    @DataProvider(name = "Correct find rubik's by form")
    private Object[][] findRubiksByFormCorrectProvider() {
        return new Object[][]{
                {"", 0, 12, 11},
                {"c", 0, 2, 2},
                {"cube", 0, 2, 2},
                {"dawfeawgawgaw", 1, 1, 0}
        };
    }

    @DataProvider(name = "Incorrect find rubik's by form")
    private Object[][] findRubiksByFormIncorrectProvider() {
        return new Object[][]{
                {"", -1, 1, 1},
                {"", 1, -1, 1}
        };
    }

    @DataProvider(name = "Correct find rubik's by size")
    private Object[][] findRubiksBySizeCorrectProvider() {
        return new Object[][]{
                {"", 0, 12, 11},
                {"3", 0, 5, 5},
                {"3x3", 0, 2, 2},
                {"3x3x3", 0, 2, 2},
                {"dawfeawgawgaw", 1, 1, 0}
        };
    }

    @DataProvider(name = "Incorrect find rubik's by size")
    private Object[][] findRubiksBySizeIncorrectProvider() {
        return new Object[][]{
                {"", -1, 1, 1},
                {"", 1, -1, 1}
        };
    }

    @DataProvider(name = "Correct find count rubik's by model")
    private Object[][] findCountRubiksByModelCorrectProvider() {
        return new Object[][]{
                {"", 11},
                {"x", 3}
        };
    }

    @DataProvider(name = "Correct find count rubik's by form")
    private Object[][] findCountRubiksByFormCorrectProvider() {
        return new Object[][]{
                {"", 11},
                {"c", 6}
        };
    }

    @DataProvider(name = "Correct find count rubik's by size")
    private Object[][] findCountRubiksBySizeCorrectProvider() {
        return new Object[][]{
                {"", 11},
                {"2", 1}
        };
    }

    @DataProvider(name = "Correct find count rubik's by manufacturer")
    private Object[][] findCountRubiksByManufacturerCorrectProvider() {
        return new Object[][]{
                {"", 11},
                {"g", 1}
        };
    }

    @DataProvider(name = "Correct find count rubik's by price")
    private Object[][] findCountRubiksByPriceCorrectProvider() {
        return new Object[][]{
                {"1", "2", 0},
                {"1", "2000", 11}
        };
    }

    @DataProvider(name = "Correct find rubik's by price")
    private Object[][] findRubiksByPriceCorrectProvider() {
        return new Object[][]{
                {"1", "2", 0, 1, 0},
                {"1", "2000", 0, 11, 11},
                {"1", "1000", 3, 11, 7}
        };
    }

    @DataProvider(name = "Incorrect find rubik's by price")
    private Object[][] findRubiksByPriceIncorrectProvider() {
        return new Object[][]{
                {"1", "2", -1, 1, 1},
                {"1", "2", 0, -1, 1}

        };
    }

    @DataProvider(name = "Correct find rubik's by unblocked")
    private Object[][] findRubiksByUnblockedCorrectProvider() {
        return new Object[][]{
                {1, 1, 1},
                {11, 0, 10}
        };
    }

    @DataProvider(name = "Incorrect find rubik's by unblocked")
    private Object[][] findRubiksByUnblockedIncorrectProvider() {
        return new Object[][]{
                {-1, 1, 1},
                {1, -1, 1}
        };
    }

    @DataProvider(name = "Correct update")
    private Object[][] updateCorrect() {
        return new Object[][]{
                {}
        };
    }

    @DataProvider(name = "Incorrect update")
    private Object[][] updateIncorrect() {
        return new Object[][]{
                {}
        };
    }

    @DataProvider(name = "Correct create")
    private Object[][] createCorrect() {
        return new Object[][]{
                {}
        };
    }

    @DataProvider(name = "Incorrect create")
    private Object[][] createIncorrect() {
        return new Object[][]{
                {}
        };
    }

    @Test(dataProvider = "Correct find rubik's by unblocked",
            dependsOnMethods = "testUpdateStateCorrect", groups = "find by")
    public void testFindRubiksByUnblockedCorrect(final int limit,
                                                 final int offset,
                                                 final int expected)
            throws ServiceException {
        int actual = service.findRubiksByUnblocked(limit, offset).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Incorrect find rubik's by unblocked", groups =
            "find by",
            dependsOnMethods = "testUpdateStateCorrect", expectedExceptions =
            ServiceException.class)
    public void testFindRubiksByUnblockedIncorrect(final int limit,
                                                   final int offset,
                                                   final int expected)
            throws ServiceException {
        int actual = service.findRubiksByUnblocked(limit, offset).size();
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "testUpdateStateCorrect", groups = "find by")
    public void testFindCountByUnblocked() throws ServiceException {
        int actual = service.findCountByUnblocked();
        int expected = 10;
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Correct find rubik's by manufacturer",
            groups = "find by")
    public void testFindRubiksByManufacturerCorrect(final String manufacturer,
                                                    final int limit,
                                                    final int offset,
                                                    final int expected)
            throws ServiceException {
        int actual = service.findRubiksByManufacturer(manufacturer, limit,
                offset).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Incorrect find rubik's by manufacturer", groups =
            "find by",
            expectedExceptions = ServiceException.class)
    public void testFindRubiksByManufacturerIncorrect(final String manufacturer,
                                                      final int limit,
                                                      final int offset,
                                                      final int expected)
            throws ServiceException {
        int actual = service.findRubiksByManufacturer(manufacturer, limit,
                offset).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Correct find rubik's by size", groups = "find by")
    public void testFindRubiksBySizeCorrect(final String size,
                                            final int offset,
                                            final int limit,
                                            final int expected)
            throws ServiceException {
        int actual = service.findRubiksBySize(size, offset, limit).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Incorrect find rubik's by size", groups = "find by",
            expectedExceptions = ServiceException.class)
    public void testFindRubiksBySizeIncorrect(final String size,
                                              final int offset,
                                              final int limit,
                                              final int expected)
            throws ServiceException {
        int actual = service.findRubiksBySize(size, offset, limit).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Correct find rubik's by model", groups = "find by")
    public void testFindRubiksByModelCorrect(final String model,
                                             final int offset,
                                             final int limit,
                                             final int expected)
            throws ServiceException {
        int actual = service.findRubiksByModel(model, offset, limit).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Incorrect find rubik's by model", groups = "find by",
            expectedExceptions = ServiceException.class)
    public void testFindRubiksByModelIncorrect(final String model,
                                               final int offset,
                                               final int limit,
                                               final int expected)
            throws ServiceException {
        int actual = service.findRubiksByModel(model, offset, limit).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Correct find rubik's by price", groups = "find by")
    public void testFindRubiksByRangePriceCorrect(final String minPrice,
                                                  final String maxPrice,
                                                  final int offset,
                                                  final int limit,
                                                  final int expected)
            throws ServiceException {
        int actual = service.findRubiksByRangePrice(minPrice, maxPrice,
                offset, limit).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Incorrect find rubik's by price", groups = "find by",
            expectedExceptions = ServiceException.class)
    public void testFindRubiksByRangePriceIncorrect(final String minPrice,
                                                    final String maxPrice,
                                                    final int offset,
                                                    final int limit,
                                                    final int expected)
            throws ServiceException {
        int actual = service.findRubiksByRangePrice(minPrice, maxPrice,
                offset, limit).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Correct find count rubik's by form",
            groups = "find by")
    public void testFindCountByForm(final String form, final int expected)
            throws ServiceException {
        int actual = service.findCountByForm(form);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Correct find count rubik's by price",
            groups = "find by")
    public void testFindCountByPrice(final String minPrice,
                                     final String maxPrice,
                                     final int expected)
            throws ServiceException {
        int actual = service.findCountByPrice(minPrice, maxPrice);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Correct find count rubik's by manufacturer",
            groups = "find by")
    public void testFindCountByManufacturer(final String manufacturer,
                                            final int expected)
            throws ServiceException {
        int actual = service.findCountByManufacturer(manufacturer);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Correct find rubik's by form", groups = "find by")
    public void testFindRubiksByFormCorrect(final String form,
                                            final int offset,
                                            final int limit,
                                            final int expected)
            throws ServiceException {
        int actual = service.findRubiksByForm(form, offset, limit).size();
        System.out.println(service.findRubiksByForm(form, offset, limit));
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Incorrect find rubik's by form",
            expectedExceptions = ServiceException.class, groups = "find by")
    public void testFindRubiksByFormIncorrect(final String form,
                                              final int offset,
                                              final int limit,
                                              final int expected)
            throws ServiceException {
        int actual = service.findRubiksByForm(form, offset, limit).size();
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByIdCorrect() throws ServiceException {
        long expected = 5;
        RubiksCube cube = service.findById(expected);
        long actual = cube.getId();
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByIdIncorrect() throws ServiceException {
        long expected = 20;
        RubiksCube cube = service.findById(expected);
        assertNull(cube);
    }

    @Test(dataProvider = "Correct find all", groups = "find by")
    public void testFindAllCorrect(final int offset, final int limit,
                                   final int expected) throws ServiceException {
        int actual = service.findAll(offset, limit).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Incorrect find all", expectedExceptions =
            ServiceException.class, groups = "find by")
    public void testFindAllIncorrect(final int offset, final int limit,
                                     final int expected)
            throws ServiceException {
        int actual = service.findAll(offset, limit).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Correct update", dependsOnGroups = {"find by"})
    public void testUpdateCorrect(final long id, final List<String> params)
            throws ServiceException {
        service.update(id, params);
    }

    @Test(dataProvider = "Incorrect update", dependsOnGroups = {"find by"})
    public void testUpdateIncorrect(final long id, final List<String> params) {
    }

    @Test(dataProvider = "Correct create", dependsOnGroups = {"find by"})
    public void testCreateCorrect(final List<String> parameters,
                                  final List<RawData> rawDataNew)
            throws ServiceException {
    }

    @Test(dataProvider = "Incorrect create", dependsOnGroups = {"find by"})
    public void testCreateIncorrect(final List<String> parameters,
                                    final List<RawData> rawDataNew)
            throws ServiceException {
    }

    @Test(dataProvider = "Correct find count rubik's by model",
            groups = "find by")
    public void testFindCountByModel(final String model, final int expected)
            throws ServiceException {
        int actual = service.findCountByModel(model);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Correct find count rubik's by size",
            groups = "find by")
    public void testFindCountBySize(final String size, final int expected)
            throws ServiceException {
        int actual = service.findCountBySize(size);
        assertEquals(actual, expected);
    }

    @Test
    public void testUpdateStateCorrect() throws ServiceException {
        long id = 1;
        service.updateState(id);
        RubiksCube cube = service.findById(id);
        assertTrue(cube.isBlocked());
    }

    @Test
    public void testUpdateStateIncorrect() throws ServiceException {
        long id = 12;
        service.updateState(id);
        RubiksCube cube = service.findById(id);
        assertNull(cube);
    }

    @Test
    public void testReadAllManufacturer() throws ServiceException {
        int actual = service.readAllManufacturer().size();
        int expected = 10;
        assertEquals(actual, expected);
    }

    @Test
    public void testReadAllForm() throws ServiceException {
        int actual = service.readAllForm().size();
        int expected = 6;
        assertEquals(actual, expected);
    }

    @Test
    public void testReadAllPlasticColor() throws ServiceException {
        int actual = service.readAllPlasticColor().size();
        int expected = 3;
        assertEquals(actual, expected);
    }

    @Test
    public void testFindElementCount() throws ServiceException {
        int actual = service.findElementCount();
        int expected = 11;
        assertEquals(actual, expected);
    }
}