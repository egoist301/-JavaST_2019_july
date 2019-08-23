package by.training.composite.bean;

import by.training.composite.service.parser.LexemeParser;
import by.training.composite.service.parser.ParagraphParser;
import by.training.composite.service.parser.Parser;
import by.training.composite.service.parser.SentenceParser;
import by.training.composite.service.parser.SymbolParser;
import by.training.composite.service.parser.TextParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Text test.
 */
public class TextTest {
    /**
     * Testing text compose.
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
        Component component = new Text();
        Component component1 = new Text();
        Component component2 = new Text();
        Component component3 = new Text();
        Component component4 = new Text();
        Parser parser = new TextParser();
        Parser parser1 = new ParagraphParser();
        Parser parser2 = new SentenceParser();
        Parser parser3 = new LexemeParser();
        parser.setNext(parser1);
        parser1.setNext(parser2);
        parser2.setNext(parser3);
        parser3.setNext(new SymbolParser());
        parser.parse("\tJava. World?\n\tJava Hello... Good?!\n\tOh, my god!",
                component);
        parser.parse("\tHelp world... Java hello?!", component1);
        parser.parse("", component2);
        parser.parse("\tHelp java?\n\tHelp, help, help!", component3);
        parser.parse("God.", component4);
        return new Object[][]{
                {component, "\tJava. World?\n\tJava Hello..."
                        + " Good?!\n\tOh, my god!\n"},
                {component1, "\tHelp world... Java hello?!\n"},
                {component2, "\t\n"},
                {component3, "\tHelp java?\n\tHelp, help, help!\n"},
                {component4, "\tGod.\n"}
        };
    }
}
