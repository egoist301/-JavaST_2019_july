package by.training.composite.dao.parser;

import by.training.composite.bean.Component;
import by.training.composite.bean.Symbol;

/**
 * Word parser.
 */
public class SymbolParser implements Parser {
    /**
     * Setter.
     *
     * @param nextParser parser.
     */
    @Override
    public void setNext(final Parser nextParser) {
    }

    /**
     * Parse text.
     *
     * @param text      text.
     * @param component component.
     */
    @Override
    public void parse(final String text, final Component component) {
        for (Character elem : text.toCharArray()) {
            Component symbol = new Symbol(elem);
            component.add(symbol);
        }
    }
}

