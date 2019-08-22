package by.training.composite.service.parser;

import by.training.composite.bean.Component;
import by.training.composite.bean.Symbol;
import by.training.composite.bean.Word;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Symbol parser test.
 */
public class SymbolParserTest {

    /**
     * Testing parse symbol.
     *
     * @param active   active component.
     * @param expected expected component.
     */
    @Test(groups = {"Parser group"},
            dataProvider = "parse")
    public void testParse(final Component active, final Component expected) {
        assertEquals(active.compose(), expected.compose());
    }

    /**
     * Provider.
     *
     * @return active component and expected component.
     */
    @DataProvider(name = "parse")
    public Object[][] testParseProvider() {
        Component word = new Word();
        SymbolParser parser = new SymbolParser();
        parser.parse("dawadwad", word);
        Component result = new Word();
        result.add(new Symbol('d'));
        result.add(new Symbol('a'));
        result.add(new Symbol('w'));
        result.add(new Symbol('a'));
        result.add(new Symbol('d'));
        result.add(new Symbol('w'));
        result.add(new Symbol('a'));
        result.add(new Symbol('d'));
        Component word1 = new Word();
        parser.parse("", word1);
        Component result1 = new Word();
        return new Object[][]{
                {result.getChild(0), new Symbol('d')},
                {result.getChild(1), new Symbol('a')},
                {result.getChild(2), new Symbol('w')},
                {result.getChild(3), new Symbol('a')},
                {result.getChild(4), new Symbol('d')},
                {result.getChild(5), new Symbol('w')},
                {result.getChild(6), new Symbol('a')},
                {result.getChild(7), new Symbol('d')},
                {word1, result1}
        };
    }
}
