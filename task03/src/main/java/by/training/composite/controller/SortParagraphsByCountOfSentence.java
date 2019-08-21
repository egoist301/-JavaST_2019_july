package by.training.composite.controller;

import by.training.composite.bean.Component;
import by.training.composite.service.sorter.Sorter;
import by.training.composite.service.sorter.SorterParagraphs;

/**
 * Sort paragraphs by count of sentence.
 */
public class SortParagraphsByCountOfSentence implements Command {
    /**
     * Component.
     */
    private Component component;

    /**
     * Constructor.
     *
     * @param componentNew component.
     */
    public SortParagraphsByCountOfSentence(final Component componentNew) {
        component = componentNew;
    }

    /**
     * Execute command.
     */
    @Override
    public void execute() {
        Sorter sorterParagraphs = new SorterParagraphs();
        sorterParagraphs.sort(component);
    }
}
