package by.training.matrix.view;

/**
 * MenuEntry.
 */
public abstract class MenuEntry {
    /**
     * Name menu entry.
     */
    private String title;

    /**
     * @param titleNew name menu entry.
     */
    MenuEntry(final String titleNew) {
        title = titleNew;
    }

    /**
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
