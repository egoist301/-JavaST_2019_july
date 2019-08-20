package by.training.composite.dao.parser;

import by.training.composite.bean.Component;
import by.training.composite.bean.Symbol;

import java.util.stream.Stream;

/**
 * Punctuation mark parser.
 */
public class PunctuationMarkParser implements Parser {
    /**
     * Delimiter.
     */
    private static final String REGEX = "";
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
        Stream.of(text.split(REGEX)).map(String::trim).forEach(elem -> {
            Component symbol = new Symbol(text);
            component.add(symbol);
            if (next != null) {
                next.parse(elem, symbol);
            }
        });
    }
}
