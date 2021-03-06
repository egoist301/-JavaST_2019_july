package by.training.transport.dao.reader;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;

public class DataReaderTest {

    @Test(groups = {"Data reader group"})
    public void testReadAll() {
        DataReader dataReader = new DataReader();
        List<String> stringList = dataReader.readAll("data//train.txt");
        assertFalse(stringList.isEmpty());
    }
}