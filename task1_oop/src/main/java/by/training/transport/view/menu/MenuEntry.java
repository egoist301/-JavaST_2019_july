package by.training.transport.view.menu;

public abstract class MenuEntry {
    /**
     * Name menu entry.
     */
    private String title;

    /**
     * @param titleNew name menu entry.
     */
    public MenuEntry(final String titleNew) {
        title = titleNew;
    }

    /**
     * @return name menu entry.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Runner.
     */
    public abstract void run();
}
