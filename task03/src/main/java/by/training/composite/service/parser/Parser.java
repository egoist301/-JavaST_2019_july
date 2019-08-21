package by.training.composite.service.parser;

import by.training.composite.bean.Component;

/**
 * Parser.
 */
public interface Parser {
    /**
     * Setter.
     *
     * @param nextParser parser.
     */
    void setNext(Parser nextParser);

    /**
     * Parse text.
     *
     * @param text         text.
     * @param componentNew component.
     */
    void parse(String text, Component componentNew);
}
