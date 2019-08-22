package by.training.composite.service.parser;

import by.training.composite.bean.Component;
import by.training.composite.bean.Sentence;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Sentence parser test.
 */
public class SentenceParserTest {

    /**
     * Testing parse sentence.
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
        Component sentence = new Sentence();
        Parser parser = new SentenceParser();
        Parser parser1 = new LexemeParser();
        parser.setNext(parser1);
        parser1.setNext(new SymbolParser());
        parser.parse("dwadw dwawd, wdadwa    'adwda' wdad.", sentence);
        return new Object[][]{
                {sentence.getChild(0), "dwadw"},
                {sentence.getChild(1), "dwawd,"},
                {sentence.getChild(2), "wdadwa"},
                {sentence.getChild(3), "'adwda'"},
                {sentence.getChild(4), "wdad."}
        };
    }
}
