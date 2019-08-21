package by.training.composite.service.parser;

import by.training.composite.bean.Component;
import by.training.composite.bean.Sentence;

/**
 * Paragraph parser.
 */
public class ParagraphParser implements Parser {
    /**
     * Delimiter.
     */
    private static final String REGEX = "(?<=([.!?\\t]))\\s";
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
            Component sentence = new Sentence();
            component.add(sentence);
            if (next != null) {
                next.parse(elem.trim(), sentence);
            }
        }
    }
}

