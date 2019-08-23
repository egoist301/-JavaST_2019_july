package by.training.composite.view.menu;

import by.training.composite.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Locale menu.
 */
public class LocaleMenu extends Menu {
    /**
     * Logger.
     */
    private static final Logger LOG = LogManager.getLogger();
    /**
     * Menu.
     */
    private List<MenuEntry> menuEntryList = new ArrayList<>();
    /**
     * Exit condition.
     */
    private boolean isExit = false;

    /**
     * Default constructor.
     */
    public LocaleMenu() {
        menuEntryList.add(new MenuEntry("Exit") {
            @Override
            public void run() {
                isExit = true;
            }
        });
    }

    /**
     * Runner for menu.
     */
    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (!isExit) {
            Printer.printInfo(toString());
            try {
                int choice = Integer.parseInt(reader.readLine());
                MenuEntry entry = menuEntryList.get(choice);
                entry.run();
                isExit = true;
            } catch (IOException | NumberFormatException
                    | IndexOutOfBoundsException e) {
                LOG.warn("Menu: ", e);
            }
        }
    }

    /**
     * Add menu entry.
     *
     * @param entry menu entry.
     */
    @Override
    public void addEntry(final MenuEntry entry) {
        menuEntryList.add(entry);
    }

    /**
     * String representation of an object.
     *
     * @return menu.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nMenu:\n");
        for (int i = 0; i < menuEntryList.size(); i++) {
            builder.append(String.format("%s - %s;%n", i,
                    menuEntryList.get(i).getTitle()));
        }
        return builder.toString();
    }
}
