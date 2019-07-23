package by.training.transport.view.menu;

import by.training.transport.controller.NumberPassengers;
import by.training.transport.view.Printer;

public class NumberPassengersMenuEntry extends MenuEntry {
    /**
     * @param titleNew name menu entry.
     */
    public NumberPassengersMenuEntry(final String titleNew) {
        super(titleNew);
    }

    /**
     * Runner.
     */
    @Override
    public void run() {
        NumberPassengers numberPassengers = new NumberPassengers();
        Printer.execute(numberPassengers.execute());
    }
}
