package by.training.composite.dao.parser;

import by.training.composite.bean.Component;
import by.training.composite.bean.Lexeme;

/**
 * Sentence parser.
 */
public class SentenceParser implements Parser {
    /**
     * Delimiter.
     */
    private static final String REGEX = "\\s{1,}";
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
     * Parser text.
     *
     * @param text      text.
     * @param component component.
     */
    @Override
    public void parse(final String text, final Component component) {
        for (String elem : text.split(REGEX)) {
            Component lexeme = new Lexeme();
            component.add(lexeme);
            if (next != null) {
                next.parse(elem.trim(), lexeme);
            }
        }
    }

}
