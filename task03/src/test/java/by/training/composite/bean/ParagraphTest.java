package by.training.composite.bean;

import by.training.composite.service.parser.LexemeParser;
import by.training.composite.service.parser.ParagraphParser;
import by.training.composite.service.parser.Parser;
import by.training.composite.service.parser.SentenceParser;
import by.training.composite.service.parser.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Paragraph test.
 */
public class ParagraphTest {
    /**
     * Testing paragraph compose.
     *
     * @param componentNew actual component.
     * @param sNew         result string.
     */
    @Test(groups = {"Component group"}, dataProvider = "Compose")
    public void testCompose(final Component componentNew, final String sNew) {
        assertEquals(componentNew.compose(), sNew);
    }

    /**
     * Provider.
     *
     * @return actual component and result string.
     */
    @DataProvider(name = "Compose")
    public Object[][] testComposeProvider() {
        Component component = new Paragraph();
        Component component1 = new Paragraph();
        Component component2 = new Paragraph();
        Component component3 = new Paragraph();
        Component component4 = new Paragraph();
        Parser parser = new ParagraphParser();
        Parser parser1 = new SentenceParser();
        Parser parser2 = new LexemeParser();
        parser.setNext(parser1);
        parser1.setNext(parser2);
        parser2.setNext(new SymbolParser());
        parser.parse("Java my 'love'. Help love.", component);
        parser.parse("Help world... Java hello?!", component1);
        parser.parse("", component2);
        parser.parse("Help java? Help, help, help!", component3);
        parser.parse("God.", component4);
        return new Object[][]{
                {component, "Java my 'love'. Help love."},
                {component1, "Help world... Java hello?!"},
                {component2, ""},
                {component3, "Help java? Help, help, help!"},
                {component4, "God."}
        };
    }
}
