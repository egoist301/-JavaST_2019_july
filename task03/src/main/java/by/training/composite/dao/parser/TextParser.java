package by.training.composite.dao.parser;

import by.training.composite.bean.Component;
import by.training.composite.bean.Paragraph;

/**
 * Text parser.
 */
public class TextParser implements Parser {
    /**
     * Delimiter.
     */
    private static final String REGEX = "(?<=\\n)(\\s{4,}|\\t)(?=\\w)";
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
        for (String elem : text.split(REGEX)) {
            Component paragraph = new Paragraph();
            component.add(paragraph);
            if (next != null) {
                next.parse(elem, paragraph);
            }
        }
    }
}
