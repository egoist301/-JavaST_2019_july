package by.training.composite.view;

import by.training.composite.bean.Component;
import by.training.composite.service.Configuration;
import by.training.composite.bean.ResourceManager;
import by.training.composite.controller.LoadTextFromFile;
import by.training.composite.view.menu.LocaleEntryButton;
import by.training.composite.view.menu.LocaleMenu;
import by.training.composite.view.menu.Menu;
import by.training.composite.view.menu.PrintTextButton;
import by.training.composite.view.menu.SortTextButton;

/**
 * Runner.
 */
public final class Runner {
    /**
     * Default constructor.
     */
    private Runner() {

    }

    /**
     * Method for run app.
     *
     * @param args command line.
     */
    public static void main(final String[] args) {
        ResourceManager manager = ResourceManager.INSTANCE;
        LoadTextFromFile loader = new LoadTextFromFile();
        Component text = loader.load(Configuration.FILE_PATH);
        Menu firstMenu = new LocaleMenu();
        firstMenu.addEntry(new LocaleEntryButton("english"));
        firstMenu.addEntry(new LocaleEntryButton("russian"));
        firstMenu.addEntry(new LocaleEntryButton("belorussian"));
        firstMenu.run();
        Menu menu = new Menu();
        menu.addEntry(new PrintTextButton(manager.getString("print"), text));
        menu.addEntry(new SortTextButton(manager.getString("sort"), text));
        menu.run();
    }
}
