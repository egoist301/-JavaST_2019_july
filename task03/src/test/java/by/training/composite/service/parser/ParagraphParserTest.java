package by.training.composite.service.parser;

import by.training.composite.bean.Component;
import by.training.composite.bean.Paragraph;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Paragraph parser test.
 */
public class ParagraphParserTest {

    /**
     * Testing parse paragraph.
     *
     * @param active   active component.
     * @param expected expected string.
     */
    @Test(groups = {"Parser group"},
            dataProvider = "parse")
    public void testParse(final Component active, final String expected) {
        assertEquals(active.compose(), expected);
    }

    /**
     * Provider.
     *
     * @return active component and expected string.
     */
    @DataProvider(name = "parse")
    public Object[][] testParseProvider() {
        Component paragraph = new Paragraph();
        Parser parser = new ParagraphParser();
        Parser parser1 = new SentenceParser();
        Parser parser2 = new LexemeParser();
        parser.setNext(parser1);
        parser1.setNext(parser2);
        parser2.setNext(new SymbolParser());
        parser.parse("Java. World? Java Hello... Good?! Oh, my god!",
                paragraph);
        return new Object[][]{
                {paragraph.getChild(0), "Java."},
                {paragraph.getChild(1), "World?"},
                {paragraph.getChild(2), "Java Hello..."},
                {paragraph.getChild(3), "Good?!"},
                {paragraph.getChild(4), "Oh, my god!"}
        };
    }
}
