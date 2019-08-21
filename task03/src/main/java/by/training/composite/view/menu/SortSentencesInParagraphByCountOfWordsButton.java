package by.training.composite.view.menu;

import by.training.composite.bean.Component;
import by.training.composite.controller.Command;
import by.training.composite.controller.SortSentencesInParagraphByCountOfWords;

/**
 * Sort sentences in paragraph by count of words button.
 */
public class SortSentencesInParagraphByCountOfWordsButton extends MenuEntry {
    /**
     * Component.
     */
    private Component component;

    /**
     * Constructor.
     *
     * @param titleNew     name menu entry.
     * @param componentNew component.
     */
    SortSentencesInParagraphByCountOfWordsButton(final String titleNew,
                                                 final Component componentNew) {
        super(titleNew);
        component = componentNew;
    }

    /**
     * Runner.
     */
    @Override
    public void run() {
        Command command = new SortSentencesInParagraphByCountOfWords(component);
        command.execute();
    }
}
