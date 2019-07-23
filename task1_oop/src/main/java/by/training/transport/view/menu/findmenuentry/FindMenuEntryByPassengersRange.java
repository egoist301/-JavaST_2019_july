package by.training.transport.view.menu.findmenuentry;

import by.training.transport.controller.findmenubutton.FindByPassengersRange;
import by.training.transport.view.Printer;
import by.training.transport.view.menu.MenuEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindMenuEntryByPassengersRange extends MenuEntry {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @param titleNew name menu entry.
     */
    public FindMenuEntryByPassengersRange(final String titleNew) {
        super(titleNew);
    }

    /**
     * Runner.
     */
    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            int numberOfPassengersMIN = Integer.parseInt(reader.readLine());
            int numberOfPassengersMAX = Integer.parseInt(reader.readLine());
            FindByPassengersRange findByPassengersRange =
                    new FindByPassengersRange();
            Printer.execute(findByPassengersRange.execute(numberOfPassengersMIN,
                    numberOfPassengersMAX));
        } catch (IOException ex) {
            LOGGER.warn(ex);
        }
    }
}
