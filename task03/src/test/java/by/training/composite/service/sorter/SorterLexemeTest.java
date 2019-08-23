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
 * Sorter lexeme test.
 */
public class SorterLexemeTest {
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
     * Testing sort lexemes.
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
        Component component3 = new Text();
        Component component4 = new Text();
        Parser parser = getParser();
        parser.parse("\tJava. World?\n\tHello Java... Good?!\n\tOh, my god!",
                component);
        parser.parse("", component1);
        parser.parse("Java.", component2);
        parser.parse("\tThe common white space prefix is 14, so 14 white"
                + " spaces are removed from the start of each line. The"
                + " trailing blank line is stripped to leave an empty line,"
                + " which being the last line is then discarded.\n\t In other"
                + " words, moving the closing delimiter to the right of the"
                + " content has no effect, and the algorithm again preserves"
                + " the essential indentation of the content:", component3);
        parser.parse("hello java god oh my fuck", component4);
        Sorter sorter = new SorterLexeme('o');
        sorter.sort(component);
        sorter.sort(component1);
        sorter.sort(component2);
        sorter.sort(component3);
        Sorter sorter1 = new SorterLexeme('3');
        sorter1.sort(component4);
        return new Object[][]{
                {component, "\tJava. World?\n\t"
                        + "Hello Java... Good?!\n\tgod! Oh, my\n"},
                {component1, "\t\n"},
                {component2, "\tJava.\n"},
                {component3, "\tcommon from of removed so 14 14, The are each "
                        + "is line. prefix space spaces start the white white "
                        + "to The an being blank discarded. empty is is last"
                        + " leave line line line, stripped the then trailing"
                        + " which  algorithm closing content content:"
                        + " indentation moving no of of other to words, In"
                        + " again and delimiter effect, essential has"
                        + " preserves right the the the the the the\n"},
                {component4, "\tfuck god hello java my oh\n"}
        };
    }
}
