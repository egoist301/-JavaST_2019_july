package by.training.transport.view;

import by.training.transport.view.menu.FindMenu;
import by.training.transport.view.menu.Menu;
import by.training.transport.view.menu.PrintTrain;
import by.training.transport.view.menu.SortMenu;

public final class Main {
    private Main() {
    }

    /**
     * @param args command line arguments.
     */
    public static void main(final String[] args) {

        Menu menu = new Menu();
        menu.addEntry(new PrintTrain("print train"));
        menu.addEntry(new FindMenu("find in train"));
        menu.addEntry(new SortMenu("sort carriage"));
        menu.run();
    }
}
