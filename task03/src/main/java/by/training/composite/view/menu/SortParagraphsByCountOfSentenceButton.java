package by.training.composite.view.menu;

import by.training.composite.bean.Component;

/**
 * Sort paragraphs by count of sentence button.
 */
public class SortParagraphsByCountOfSentenceButton extends MenuEntry {
    /**
     * Component.
     */
    private Component component;
    /**
     * Constructor.
     *
     * @param titleNew name menu entry.
     * @param componentNew component.
     */
    SortParagraphsByCountOfSentenceButton(final String titleNew,
                                          final Component componentNew) {
        super(titleNew);
        component = componentNew;
    }

    /**
     * Runner.
     */
    @Override
    public void run() {

    }
}
