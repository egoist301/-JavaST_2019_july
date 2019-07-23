package by.training.transport.view.menu;

import by.training.transport.view.menu.findmenuentry.FindMenuEntryByLuggage;
import by.training.transport.view.menu.findmenuentry.FindMenuEntryByPassengers;
import by.training.transport.view.menu.findmenuentry.FindMenuEntryByPassengersRange;
import by.training.transport.view.menu.findmenuentry.FindMenuEntryById;

public class FindMenu extends MenuEntry {
    /**
     * @param titleNew name menu entry.
     */
    public FindMenu(final String titleNew) {
        super(titleNew);
    }

    /**
     * Runner.
     */
    @Override
    public void run() {
        Menu menu = new Menu();
        menu.addEntry(new FindMenuEntryById("by ID"));
        menu.addEntry(new FindMenuEntryByPassengers("by passengers"));
        menu.addEntry(new FindMenuEntryByLuggage("by luggage"));
        menu.addEntry(new FindMenuEntryByPassengersRange("by passengers"
                + " range"));
        menu.run();
    }
}
