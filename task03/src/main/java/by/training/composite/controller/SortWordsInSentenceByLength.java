package by.training.composite.controller;

import by.training.composite.bean.Component;
import by.training.composite.service.sorter.Sorter;
import by.training.composite.service.sorter.SorterWords;

/**
 * Sort words in sentence by length.
 */
public class SortWordsInSentenceByLength implements Command {
    /**
     * Component.
     */
    private Component component;

    /**
     * Constructor.
     *
     * @param componentNew component.
     */
    public SortWordsInSentenceByLength(final Component componentNew) {
        component = componentNew;
    }

    /**
     * Execute command.
     */
    @Override
    public void execute() {
        Sorter sorter = new SorterWords();
        sorter.sort(component);
    }
}
