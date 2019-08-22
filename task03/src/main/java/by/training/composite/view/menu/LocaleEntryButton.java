package by.training.composite.view.menu;

import by.training.composite.controller.LocaleApp;

/**
 * Locale entry button.
 */
public class LocaleEntryButton extends MenuEntry {
    /**
     * Constructor.
     *
     * @param titleNew name menu entry.
     */
    public LocaleEntryButton(final String titleNew) {
        super(titleNew);
    }

    /**
     * Runner.
     */
    @Override
    public void run() {
        LocaleApp localeApp = new LocaleApp(getTitle());
        localeApp.execute();
    }
}
