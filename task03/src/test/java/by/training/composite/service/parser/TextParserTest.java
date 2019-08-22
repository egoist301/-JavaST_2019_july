package by.training.composite.service.parser;

import by.training.composite.bean.Component;
import by.training.composite.bean.Text;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Text parser test.
 */
public class TextParserTest {

    /**
     * Testing parse text.
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
        Component paragraph = new Text();
        Parser parser = new TextParser();
        Parser parser1 = new ParagraphParser();
        Parser parser2 = new SentenceParser();
        Parser parser3 = new LexemeParser();
        parser.setNext(parser1);
        parser1.setNext(parser2);
        parser2.setNext(parser3);
        parser3.setNext(new SymbolParser());
        parser.parse("\tJava. World?\n\tJava Hello... Good?!\n\tOh, my god!",
                paragraph);
        return new Object[][]{
                {paragraph.getChild(0), "Java. World?"},
                {paragraph.getChild(1), "Java Hello... Good?!"},
                {paragraph.getChild(2), "Oh, my god!"}
        };
    }
}
