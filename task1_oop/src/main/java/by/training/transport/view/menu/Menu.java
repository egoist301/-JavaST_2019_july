package by.training.transport.view.menu;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu {
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
    public Menu() {
        menuEntryList.add(new MenuEntry("Exit") {
            @Override
            public void run() {
                isExit = true;
            }
        });
    }

    /**
     * Runner.
     */
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (!isExit) {
            LOG.info(toString());
            try {
                int choice = Integer.parseInt(reader.readLine());
                MenuEntry entry = menuEntryList.get(choice);
                entry.run();
            } catch (IOException e) {
                LOG.warn("Menu: ", e);
            }
        }
    }

    /**
     * @param entry menu entry.
     */
    public void addEntry(final MenuEntry entry) {
        if (entry != null) {
            menuEntryList.add(entry);
        }
    }

    /**
     * @return menu.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nMenu:\n");
        for (int i = 0; i < menuEntryList.size(); i++) {
            builder.append(String.format("%s - %s;\n", i,
                    menuEntryList.get(i).getTitle()));
        }
        return builder.toString();
    }
}
