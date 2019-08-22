package by.training.composite.view.menu;

import by.training.composite.bean.Component;
import by.training.composite.bean.ResourceManager;

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
        ResourceManager manager = ResourceManager.INSTANCE;
        Menu menu = new Menu();
        menu.addEntry(new SortParagraphsByCountOfSentenceButton(
                manager.getString("sort1"), component));
        menu.addEntry(new SortWordsInSentenceByLengthButton(
                manager.getString("sort2"), component));
        menu.addEntry(new SortSentencesInParagraphByCountOfWordsButton(
                manager.getString("sort3"), component));
        menu.addEntry(new SortLexemeInSentencesButton(
                manager.getString("sort4"), component));
        menu.run();
    }
}
