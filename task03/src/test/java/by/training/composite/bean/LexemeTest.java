package by.training.composite.bean;

import by.training.composite.service.parser.LexemeParser;
import by.training.composite.service.parser.Parser;
import by.training.composite.service.parser.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Lexeme test.
 */
public class LexemeTest {
    /**
     * Testing lexeme compose.
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
        Component component = new Lexeme();
        Component component1 = new Lexeme();
        Component component2 = new Lexeme();
        Component component3 = new Lexeme();
        Component component4 = new Lexeme();
        Component component5 = new Lexeme();
        Component component6 = new Lexeme();
        Component component7 = new Lexeme();
        Parser parser = new LexemeParser();
        parser.setNext(new SymbolParser());
        parser.parse("word,", component);
        parser.parse("love.", component1);
        parser.parse("world...", component2);
        parser.parse("hello?!", component3);
        parser.parse("", component4);
        parser.parse("java?", component5);
        parser.parse("help!", component6);
        parser.parse("god", component7);
        return new Object[][]{
                {component, "word,"},
                {component1, "love."},
                {component2, "world..."},
                {component3, "hello?!"},
                {component4, ""},
                {component5, "java?"},
                {component6, "help!"},
                {component7, "god"}
        };
    }
}
