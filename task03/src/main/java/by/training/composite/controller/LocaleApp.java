package by.training.composite.controller;

import by.training.composite.service.locale.ResourceHandler;

/**
 * Locale app.
 */
public class LocaleApp implements Command {
    /**
     * Title.
     */
    private String title;

    /**
     * Constructor.
     *
     * @param titleNew title.
     */
    public LocaleApp(final String titleNew) {
        title = titleNew;
    }

    /**
     * Execute command.
     */
    @Override
    public void execute() {
        ResourceHandler handler = new ResourceHandler();
        handler.locale(title);
    }
}
