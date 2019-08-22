package by.training.composite.dao.reader;

import by.training.composite.bean.Configuration;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DataReaderTest {

    @Test(groups = {"Data reader group"})
    public void testReadAll() {
        DataReader dataReader = new DataReader();
        String text = dataReader.readAll(Configuration.FILE_PATH);
        assertFalse(text.isEmpty());
    }
}