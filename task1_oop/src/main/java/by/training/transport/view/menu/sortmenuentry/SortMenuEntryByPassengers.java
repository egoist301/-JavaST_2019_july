package by.training.transport.view.menu.sortmenuentry;

import by.training.transport.controller.sortmenubutton.SortByPassengers;
import by.training.transport.view.Printer;
import by.training.transport.view.menu.MenuEntry;

public class SortMenuEntryByPassengers extends MenuEntry {
    /**
     * @param titleNew name menu entry.
     */
    public SortMenuEntryByPassengers(final String titleNew) {
        super(titleNew);
    }

    /**
     * Runner.
     */
    @Override
    public void run() {
        SortByPassengers sortByPassengers = new SortByPassengers();
        Printer.execute(sortByPassengers.execute());
    }
}
