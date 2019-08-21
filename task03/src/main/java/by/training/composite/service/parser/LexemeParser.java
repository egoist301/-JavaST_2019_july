package by.training.composite.service.parser;

import by.training.composite.bean.Component;
import by.training.composite.bean.PunctuationMark;
import by.training.composite.bean.Word;

/**
 * Lexeme parser.
 */
public class LexemeParser implements Parser {
    /**
     * Delimiter.
     */
    private static final String REGEX = "\\b(?=\\W+\\z)"; //TODO fix regex
    /**
     * Parser.
     */
    private Parser next;

    /**
     * Setter.
     *
     * @param nextParser parser.
     */
    @Override
    public void setNext(final Parser nextParser) {
        next = nextParser;
    }

    /**
     * Parse text.
     *
     * @param text      text.
     * @param component component.
     */
    @Override
    public void parse(final String text, final Component component) {
        String[] strings = text.split(REGEX);
        Component word = new Word();
        component.add(word);
        if (next != null) {
            next.parse(strings[0].trim(), word);
        }
        if (strings.length == 2) {
            Component punctuationMark = new PunctuationMark();
            component.add(punctuationMark);
            if (next != null) {
                next.parse(strings[1].trim(), punctuationMark);
            }
        }
    }
}


