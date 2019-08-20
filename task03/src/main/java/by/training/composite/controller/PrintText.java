package by.training.composite.controller;

import by.training.composite.bean.Component;
import by.training.composite.view.Printer;

/**
 * Print text.
 */
public class PrintText {
    /**
     * Print text.
     *
     * @param componentNew component.
     */
    public void execute(final Component componentNew) {
        Printer.printInfo(componentNew.compose());
    }
}
