package by.training.transport.view.menu;

import by.training.transport.view.menu.sortmenuentry.SortMenuEntryByLuggage;
import by.training.transport.view.menu.sortmenuentry.SortMenuEntryByPassengers;
import by.training.transport.view.menu.sortmenuentry.SortMenuEntryByPassengersThenLuggage;

public class SortMenu extends MenuEntry {
    /**
     * @param titleNew name menu entry.
     */
    public SortMenu(final String titleNew) {
        super(titleNew);
    }

    /**
     * Runner.
     */
    @Override
    public void run() {
        Menu menu = new Menu();
        menu.addEntry(new SortMenuEntryByPassengers("by passengers"));
        menu.addEntry(new SortMenuEntryByLuggage("by luggage"));
        menu.addEntry(new SortMenuEntryByPassengersThenLuggage("by"
                + " passengers, then by luggage"));
        menu.run();
    }
}
