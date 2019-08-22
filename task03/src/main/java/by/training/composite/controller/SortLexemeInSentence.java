package by.training.composite.controller;

import by.training.composite.bean.Component;
import by.training.composite.service.sorter.Sorter;
import by.training.composite.service.sorter.SorterLexeme;

/**
 * Sort lexeme in sentence.
 */
public class SortLexemeInSentence implements Command {
    /**
     * Component.
     */
    private Component component;
    /**
     * Symbol.
     */
    private char symbol;

    /**
     * Constructor.
     * @param componentNew component.
     * @param symbolNew symbol.
     */
    public SortLexemeInSentence(final Component componentNew,
                                final char symbolNew) {
        component = componentNew;
        symbol = symbolNew;
    }

    /**
     * Execute command.
     */
    @Override
    public void execute() {
        Sorter sorter = new SorterLexeme(symbol);
        sorter.sort(component);
    }
}
