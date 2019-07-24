package by.training.transport.view.menu.findmenuentry;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.controller.findmenubutton.FindById;
import by.training.transport.view.Printer;
import by.training.transport.view.menu.MenuEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindMenuEntryById extends MenuEntry {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @param titleNew name menu entry.
     */
    public FindMenuEntryById(final String titleNew) {
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
            long id = Long.parseLong(reader.readLine());
            FindById findById = new FindById();
            for (PassengerCarriage passengerCarriage : findById.find(id)) {
                Printer.printInfo(passengerCarriage);
            }
        } catch (IOException ex) {
            LOGGER.warn(ex);
        }
    }
}
