package by.training.matrix.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Thread validation test.
 */
public class ThreadValidationTest {

    /**
     * Testing check of correct count of threads.
     * Positive way.
     *
     * @param listNew list of arrays of strings.
     */
    @Test(groups = {"Thread validation group"},
            dataProvider = "correct count of threads positive")
    public void testIsCorrectCountOfThreadsPositive(
            final List<String[]> listNew) {

       assertTrue(ThreadValidation.isCorrectCountOfThreads(listNew));
    }

    /**
     * Provider.
     *
     * @return list of arrays of strings.
     */
    @DataProvider(name = "correct count of threads positive")
    public Object[][] testIsCorrectCountOfThreadsPositiveProvider() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[1]);
        list.add(new String[1]);
        list.add(new String[1]);
        list.add(new String[1]);

        List<String[]> list1 = new ArrayList<>();
        list1.add(new String[1]);
        list1.add(new String[1]);
        list1.add(new String[1]);
        list1.add(new String[1]);
        list1.add(new String[1]);

        List<String[]> list2 = new ArrayList<>();
        list2.add(new String[1]);
        list2.add(new String[1]);
        list2.add(new String[1]);
        list2.add(new String[1]);
        list2.add(new String[1]);
        list2.add(new String[1]);

        return new Object[][]{
                {list},
                {list1},
                {list2}
        };
    }

    /**
     * Testing check of correct count of threads.
     * Negative way.
     *
     * @param listNew list of arrays of strings.
     */
    @Test(groups = {"Thread validation group"},
            dataProvider = "correct count of threads negative")
    public void testIsCorrectCountOfThreadsNegative(
            final List<String[]> listNew) {
        assertFalse(ThreadValidation.isCorrectCountOfThreads(listNew));
    }

    /**
     * Provider.
     *
     * @return list of arrays of strings.
     */
    @DataProvider(name = "correct count of threads negative")
    public Object[][] testIsCorrectCountOfThreadsNegativeProvider() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[1]);
        list.add(new String[1]);
        list.add(new String[1]);

        List<String[]> list1 = new ArrayList<>();
        list1.add(new String[1]);
        list1.add(new String[1]);
        list1.add(new String[1]);
        list1.add(new String[1]);
        list1.add(new String[1]);
        list1.add(new String[1]);
        list1.add(new String[1]);
        list1.add(new String[1]);
        list1.add(new String[1]);
        list1.add(new String[1]);
        list1.add(new String[1]);
        list1.add(new String[1]);
        list1.add(new String[1]);

        List<String[]> list2 = new ArrayList<>();
        list2.add(new String[1]);
        list2.add(new String[1]);
        list2.add(new String[1]);
        list2.add(new String[1]);
        list2.add(new String[1]);
        list2.add(new String[1]);
        list2.add(new String[1]);

        return new Object[][]{
                {list},
                {list1},
                {list2}
        };
    }

    /**
     * Testing check of unique number.
     * Positive way.
     *
     * @param listNew list of arrays of strings.
     */
    @Test(groups = {"Thread validation group"},
            dataProvider = "unique number positive")
    public void testIsUniqueNumbersPositive(final List<String[]> listNew) {
        assertTrue(ThreadValidation.isUniqueNumbers(listNew));
    }

    /**
     * Provider.
     *
     * @return list of arrays of strings.
     */
    @DataProvider(name = "unique number positive")
    public Object[][] testIsUniqueNumbersPositiveProvider() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"2"});
        list.add(new String[]{"1"});
        list.add(new String[]{"3"});
        list.add(new String[]{"4"});

        List<String[]> list1 = new ArrayList<>();
        list1.add(new String[]{"2", "a"});
        list1.add(new String[]{"1"});
        list1.add(new String[]{"3"});
        list1.add(new String[]{"4"});
        return new Object[][]{
                {list},
                {list1}
        };
    }

    /**
     * Testing check of unique number.
     * Negative way.
     *
     * @param listNew list of arrays of strings.
     */
    @Test(groups = {"Thread validation group"},
            dataProvider = "unique number negative")
    public void testIsUniqueNumbersNegative(final List<String[]> listNew) {
        assertFalse(ThreadValidation.isUniqueNumbers(listNew));
    }

    /**
     * Provider.
     *
     * @return list of arrays of strings.
     */
    @DataProvider(name = "unique number negative")
    public Object[][] testIsUniqueNumbersNegativeProvider() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"2"});
        list.add(new String[]{"4"});
        list.add(new String[]{"3"});
        list.add(new String[]{"4"});

        List<String[]> list1 = new ArrayList<>();
        list1.add(new String[]{"2", "a"});
        list1.add(new String[]{"4"});
        list1.add(new String[]{"3"});
        list1.add(new String[]{"4"});

        List<String[]> list2 = new ArrayList<>();
        list2.add(new String[]{"2"});
        list2.add(new String[]{"4", "a"});
        list2.add(new String[]{"3"});
        list2.add(new String[]{"4"});
        return new Object[][]{
                {list},
                {list1},
                {list2}
        };
    }

}
