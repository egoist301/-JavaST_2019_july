package by.training.composite.service.sorter;

import by.training.composite.bean.Component;
import by.training.composite.bean.Text;
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
 * Sorter words test.
 */
public class SorterWordsTest {
    /**
     * Getter.
     *
     * @return parser.
     */
    private Parser getParser() {
        Parser textParser = new TextParser();
        Parser paragraphParser = new ParagraphParser();
        Parser sentenceParser = new SentenceParser();
        Parser lexemeParser = new LexemeParser();
        Parser parser = new SymbolParser();
        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(parser);
        return textParser;
    }

    /**
     * Testing sort words.
     *
     * @param componentNew actual component.
     * @param sNew         result string.
     */
    @Test(groups = {"Sorter group"}, dataProvider = "sorter")
    public void testSort(final Component componentNew, final String sNew) {
        assertEquals(componentNew.compose(), sNew);
    }

    /**
     * Provider.
     *
     * @return actual component and expected string.
     */
    @DataProvider(name = "sorter")
    public Object[][] testSortProvider() {
        Component component = new Text();
        Component component1 = new Text();
        Component component2 = new Text();
        Parser parser = getParser();
        parser.parse("\tJava. World?\n\tHello Java... Good?!\n\tOh, my god!",
                component);
        parser.parse("", component1);
        parser.parse("Java.", component2);
        Sorter sorter = new SorterWords();
        sorter.sort(component);
        sorter.sort(component1);
        sorter.sort(component2);
        return new Object[][]{
                {component, "\tJava. World?\n\t"
                        + "Java... Hello Good?!\n\tOh, my god!\n"},
                {component1, "\t\n"},
                {component2, "\tJava.\n"}
        };
    }
}
