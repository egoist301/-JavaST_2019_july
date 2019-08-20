package by.training.composite.view.menu;

import by.training.composite.bean.Component;

/**
 * Sort words in sentence by length.
 */
public class SortWordsInSentenceByLengthButton extends MenuEntry {
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
    SortWordsInSentenceByLengthButton(final String titleNew,
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
