package by.training.composite.service.parser;

import by.training.composite.bean.Component;
import by.training.composite.bean.Lexeme;
import by.training.composite.bean.PunctuationMark;
import by.training.composite.bean.Word;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Lexeme parser test.
 */
public class LexemeParserTest {

    /**
     * Testing parse lexeme.
     *
     * @param active   active component.
     * @param expected expected component.
     */
    @Test(groups = {"Parser group"},
            dataProvider = "parser")
    public void testParse(final Component active, final Component expected) {
        assertEquals(active.compose(), expected.compose());
    }

    /**
     * Provider.
     *
     * @return active and expected component.
     */
    @DataProvider(name = "parser")
    public Object[][] testParseProvider() {
        Component lexeme = new Lexeme();
        Parser parser = new LexemeParser();
        Parser parser1 = new SymbolParser();
        parser.setNext(parser1);
        parser.parse("alow,", lexeme);
        Component result = new Lexeme();
        Component word = new Word();
        Component word1 = new Word();
        Component word2 = new Word();
        Component mark = new PunctuationMark();
        Component mark1 = new PunctuationMark();
        result.add(word);
        result.add(mark);
        parser1.parse("alow", word);
        parser1.parse(",", mark);
        Component lexeme1 = new Lexeme();
        parser.parse("wa", lexeme1);
        Component result1 = new Lexeme();
        result1.add(word1);
        parser1.parse("wa", word1);
        Component result2 = new Lexeme();
        Component lexeme2 = new Lexeme();
        parser.parse("wa...", lexeme2);
        result2.add(word2);
        result2.add(mark1);
        parser1.parse("wa", word2);
        parser1.parse("...", mark1);
        return new Object[][]{
                {lexeme.getChild(0), result.getChild(0)},
                {lexeme.getChild(1), result.getChild(1)},
                {lexeme1.getChild(0), result1.getChild(0)},
                {lexeme2.getChild(0), result2.getChild(0)},
                {lexeme2.getChild(1), result2.getChild(1)},
        };
    }
}
