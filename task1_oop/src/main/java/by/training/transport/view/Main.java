package by.training.transport.view;

import by.training.transport.controller.LoadTrain;
import by.training.transport.view.menu.FindMenu;
import by.training.transport.view.menu.Menu;
import by.training.transport.view.menu.PrintTrain;
import by.training.transport.view.menu.SortMenu;
import by.training.transport.view.menu.NumberLuggageMenuEntry;
import by.training.transport.view.menu.NumberPassengersMenuEntry;

public final class Main {
    private Main() {
    }

    /**
     * @param args command line arguments.
     */
    public static void main(final String[] args) {
        LoadTrain loadTrain = new LoadTrain();
        loadTrain.load("data/train.txt");
        Menu menu = new Menu();
        menu.addEntry(new PrintTrain("print train"));
        menu.addEntry(new FindMenu("find in train"));
        menu.addEntry(new SortMenu("sort carriage"));
        menu.addEntry(new NumberLuggageMenuEntry("calculate number"
                + " of luggage in train"));
        menu.addEntry(new NumberPassengersMenuEntry("calculate number"
                + " of passengers in train"));
        menu.run();
    }
}
