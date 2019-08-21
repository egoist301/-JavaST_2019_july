package by.training.composite.controller;

import by.training.composite.bean.Component;
import by.training.composite.service.sorter.Sorter;
import by.training.composite.service.sorter.SorterSentences;

/**
 * Sort sentences in paragraph by count of words.
 */
public class SortSentencesInParagraphByCountOfWords implements Command {
    /**
     * Component.
     */
    private Component component;

    /**
     * Constructor.
     *
     * @param componentNew component.
     */
    public SortSentencesInParagraphByCountOfWords(
            final Component componentNew) {
        component = componentNew;
    }

    /**
     * Execute command.
     */
    @Override
    public void execute() {
        Sorter sorter = new SorterSentences();
        sorter.sort(component);
    }
}
