package by.training.transport.view.menu;

import by.training.transport.controller.NumberLuggage;
import by.training.transport.view.Printer;

public class NumberLuggageMenuEntry extends MenuEntry {
    /**
     * @param titleNew name menu entry.
     */
    public NumberLuggageMenuEntry(final String titleNew) {
        super(titleNew);
    }

    /**
     * Runner.
     */
    @Override
    public void run() {
        NumberLuggage numberLuggage = new NumberLuggage();
        Printer.printInfo(numberLuggage.execute());
    }
}
