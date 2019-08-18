package by.training.composite.view.menu;

/**
 * MenuEntry.
 */
public abstract class MenuEntry {
    /**
     * Name menu entry.
     */
    private String title;

    /**
     * Constructor.
     *
     * @param titleNew name menu entry.
     */
    MenuEntry(final String titleNew) {
        title = titleNew;
    }

    /**
     * Getter.
     *
     * @return name menu entry.
     */
    String getTitle() {
        return title;
    }

    /**
     * Runner.
     */
    public abstract void run();
}
