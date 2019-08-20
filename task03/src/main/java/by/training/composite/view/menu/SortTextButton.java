package by.training.composite.view.menu;

import by.training.composite.bean.Component;

/**
 * Sort text button.
 * Submenu.
 */
public class SortTextButton extends MenuEntry {
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
    public SortTextButton(final String titleNew, final Component componentNew) {
        super(titleNew);
        component = componentNew;
    }

    /**
     * Runner.
     */
    @Override
    public void run() {
        Menu menu = new Menu();
        menu.addEntry(new SortParagraphsByCountOfSentenceButton("paragraphs"
                + " by count of sentence", component));
        menu.addEntry(new SortWordsInSentenceByLengthButton("words in sentence"
                + " by length", component));
        menu.addEntry(new SortSentencesInParagraphByCountOfWordsButton(
                "sentences in paragraph by count of words", component));
        menu.run();
    }
}
