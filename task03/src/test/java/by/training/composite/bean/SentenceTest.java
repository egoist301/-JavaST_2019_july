package by.training.composite.bean;

import by.training.composite.service.parser.LexemeParser;
import by.training.composite.service.parser.Parser;
import by.training.composite.service.parser.SentenceParser;
import by.training.composite.service.parser.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Sentence test.
 */
public class SentenceTest {
    /**
     * Testing sentence compose.
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
        Component component = new Sentence();
        Component component1 = new Sentence();
        Component component2 = new Sentence();
        Component component3 = new Sentence();
        Component component4 = new Sentence();
        Component component5 = new Sentence();
        Component component6 = new Sentence();
        Component component7 = new Sentence();
        Parser parser = new SentenceParser();
        Parser parser1 = new LexemeParser();
        parser.setNext(parser1);
        parser1.setNext(new SymbolParser());
        parser.parse("Java my 'love'.", component);
        parser.parse("Help love.", component1);
        parser.parse("Help world...", component2);
        parser.parse("Java      hello?!", component3);
        parser.parse("", component4);
        parser.parse("Help java?", component5);
        parser.parse("Help, help, help!", component6);
        parser.parse("God.", component7);
        return new Object[][]{
                {component, "Java my 'love'."},
                {component1, "Help love."},
                {component2, "Help world..."},
                {component3, "Java hello?!"},
                {component4, ""},
                {component5, "Help java?"},
                {component6, "Help, help, help!"},
                {component7, "God."}
        };
    }
}
