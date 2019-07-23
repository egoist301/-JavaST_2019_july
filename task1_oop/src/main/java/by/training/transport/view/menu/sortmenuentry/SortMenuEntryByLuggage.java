package by.training.transport.view.menu.sortmenuentry;

import by.training.transport.controller.sortmenubutton.SortByLuggage;
import by.training.transport.view.Printer;
import by.training.transport.view.menu.MenuEntry;

public class SortMenuEntryByLuggage extends MenuEntry {
    /**
     * @param titleNew name menu entry.
     */
    public SortMenuEntryByLuggage(final String titleNew) {
        super(titleNew);
    }

    /**
     * Runner.
     */
    @Override
    public void run() {
        SortByLuggage sortByLuggage = new SortByLuggage();
        Printer.execute(sortByLuggage.execute());
    }
}
