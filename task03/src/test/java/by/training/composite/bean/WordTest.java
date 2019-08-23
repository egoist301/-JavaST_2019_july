package by.training.composite.bean;

import by.training.composite.service.parser.Parser;
import by.training.composite.service.parser.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Word test.
 */
public class WordTest {
    /**
     * Testing word compose.
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
        Component component = new Word();
        Component component1 = new Word();
        Component component2 = new Word();
        Component component3 = new Word();
        Component component4 = new Word();
        Parser parser = new SymbolParser();
        parser.parse("word", component);
        parser.parse("love", component1);
        parser.parse("world", component2);
        parser.parse("hello", component3);
        parser.parse("", component4);
        return new Object[][]{
                {component, "word"},
                {component1, "love"},
                {component2, "world"},
                {component3, "hello"},
                {component4, ""}
        };
    }
}
