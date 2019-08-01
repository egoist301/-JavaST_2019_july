package by.training.transport.dao.parser;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class DataParserTest {

    @Test(groups = {"Data parser group"})
    public void testGetLines() { //TODO больше тестов и разнообразия
        List<String> stringList = new ArrayList<>();
        stringList.add("coupe 1 2 3");
        stringList.add("placecart 3 2 1");
        List<String[]> strings = new ArrayList<>();
        strings.add(new String[]{"coupe", "1", "2", "3"});
        strings.add(new String[]{"placecart", "3", "2", "1"});
        DataParser dataParser = new DataParser();
        assertEquals(dataParser.getLines(stringList), strings);
    }
}