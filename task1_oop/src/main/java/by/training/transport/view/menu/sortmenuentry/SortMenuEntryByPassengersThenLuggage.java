package by.training.transport.view.menu.sortmenuentry;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.controller.sortmenubutton.SortByPassengersThenLuggage;
import by.training.transport.view.Printer;
import by.training.transport.view.menu.MenuEntry;

public class SortMenuEntryByPassengersThenLuggage extends MenuEntry {
    /**
     * @param titleNew name menu entry.
     */
    public SortMenuEntryByPassengersThenLuggage(final String titleNew) {
        super(titleNew);
    }

    /**
     * Runner.
     */
    @Override
    public void run() {
        SortByPassengersThenLuggage sortByPassengersThenLuggage =
                new SortByPassengersThenLuggage();
        for (PassengerCarriage passengerCarriage
                : sortByPassengersThenLuggage.sort()) {
            Printer.printInfo(passengerCarriage);
        }
    }
}
