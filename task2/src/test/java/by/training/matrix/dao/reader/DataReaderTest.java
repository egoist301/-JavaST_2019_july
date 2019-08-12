package by.training.matrix.dao.reader;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;

/**
 * Data reader test.
 */
public class DataReaderTest {

    /**
     * Testing reading from file.
     */
    @Test(groups = {"Data reader group"})
    public void testReadAll() {
        DataReader dataReader = new DataReader();
        List<String> stringList = dataReader.readAll("data//matrix.txt");
        assertFalse(stringList.isEmpty());
    }
}
