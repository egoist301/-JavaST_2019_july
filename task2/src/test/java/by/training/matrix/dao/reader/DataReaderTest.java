package by.training.matrix.dao.reader;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class DataReaderTest {

    @Test(groups = {"Data reader group"})
    public void testReadAll() {
        DataReader dataReader = new DataReader();
        List<String> stringList = dataReader.readAll("data//matrix.txt");
        assertFalse(stringList.isEmpty());
    }
}