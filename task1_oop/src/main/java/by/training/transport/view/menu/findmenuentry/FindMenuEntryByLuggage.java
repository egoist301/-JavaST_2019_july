package by.training.transport.view.menu.findmenuentry;

import by.training.transport.controller.findmenubutton.FindByLuggage;
import by.training.transport.view.Printer;
import by.training.transport.view.menu.MenuEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindMenuEntryByLuggage extends MenuEntry {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @param titleNew name menu entry.
     */
    public FindMenuEntryByLuggage(final String titleNew) {
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
            int numberOfLuggage = Integer.parseInt(reader.readLine());
            FindByLuggage findByLuggage = new FindByLuggage();
            Printer.execute(findByLuggage.execute(numberOfLuggage));
        } catch (IOException ex) {
            LOGGER.warn(ex);
        }
    }
}
