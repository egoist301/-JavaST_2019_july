package by.training.composite.controller;

import by.training.composite.bean.Component;
import by.training.composite.view.Printer;

/**
 * Print text.
 */
public class PrintText implements Command {
    /**
     * Component.
     */
    private Component component;

    /**
     * Constructor.
     *
     * @param componentNew component.
     */
    public PrintText(final Component componentNew) {
        component = componentNew;
    }

    /**
     * Print text.
     */
    @Override
    public void execute() {
        Printer.printInfo(component.compose());
    }
}
