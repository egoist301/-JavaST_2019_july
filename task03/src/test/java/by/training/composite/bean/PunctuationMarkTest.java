package by.training.composite.bean;

import by.training.composite.service.parser.Parser;
import by.training.composite.service.parser.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Punctuation mark test.
 */
public class PunctuationMarkTest {
    /**
     * Testing punctuation mark compose.
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
        Component component = new PunctuationMark();
        Component component1 = new PunctuationMark();
        Component component2 = new PunctuationMark();
        Component component3 = new PunctuationMark();
        Component component4 = new PunctuationMark();
        Component component5 = new PunctuationMark();
        Component component6 = new PunctuationMark();
        Parser parser = new SymbolParser();
        parser.parse("...", component);
        parser.parse("?!", component1);
        parser.parse("?", component2);
        parser.parse("!", component3);
        parser.parse(".", component4);
        parser.parse(",", component5);
        parser.parse("", component6);
        return new Object[][]{
                {component, "..."},
                {component1, "?!"},
                {component2, "?"},
                {component3, "!"},
                {component4, "."},
                {component5, ","},
                {component6, ""}
        };
    }
}
