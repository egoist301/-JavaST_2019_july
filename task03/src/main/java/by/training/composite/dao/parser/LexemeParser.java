package by.training.composite.dao.parser;

import by.training.composite.bean.Component;
import by.training.composite.bean.Word;

/**
 * Lexeme parser.
 */
public class LexemeParser implements Parser {
    /**
     * Delimiter.
     */
    private static final String REGEX =
            "([\\\\.,!\\\\?:;@]{1})|([^\\\\.,!\\\\?:;@]*)";
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
        System.out.println(text);
        for (String elem : text.split(REGEX)) {
            Component word = new Word();
            component.add(word);
            if (next != null) {
                next.parse(elem, word);
            }
        }
    }
}

