package by.training.composite.view.menu;

import by.training.composite.bean.Component;
import by.training.composite.controller.Command;
import by.training.composite.controller.PrintText;

/**
 * Print text button.
 */
public class PrintTextButton extends MenuEntry {
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
    public PrintTextButton(final String titleNew,
                           final Component componentNew) {
        super(titleNew);
        component = componentNew;
    }

    /**
     * Runner.
     */
    @Override
    public void run() {
        Command command = new PrintText(component);
        command.execute();
    }
}
