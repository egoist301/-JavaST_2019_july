package by.training.composite.view;

import by.training.composite.bean.Component;
import by.training.composite.controller.LoadTextFromFile;
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
        LoadTextFromFile loader = new LoadTextFromFile();
        Component text = loader.load("data//text.txt");
        Menu menu = new Menu();
        menu.addEntry(new PrintTextButton("print text", text));
        menu.addEntry(new SortTextButton("sort text", text));
        menu.run();
    }
}
