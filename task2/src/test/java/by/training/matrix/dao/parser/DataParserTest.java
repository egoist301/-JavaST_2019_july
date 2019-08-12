package by.training.matrix.dao.parser;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Data parser test.
 */
public class DataParserTest {

    /**
     * Testing parsing data.
     */
    @Test(groups = {"Data parser group"})
    public void testGetLines() {
        List<String> stringList = new ArrayList<>();
        stringList.add("cda 1 2 3");
        stringList.add("plad");
        stringList.add("");
        List<String[]> strings = new ArrayList<>();
        strings.add(new String[]{"cda", "1", "2", "3"});
        strings.add(new String[]{"plad"});
        strings.add(new String[]{""});
        DataParser dataParser = new DataParser();
        assertEquals(dataParser.getLines(stringList), strings);
    }
}
