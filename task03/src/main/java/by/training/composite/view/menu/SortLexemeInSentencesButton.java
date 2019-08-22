package by.training.composite.view.menu;

import by.training.composite.bean.Component;
import by.training.composite.bean.ResourceManager;
import by.training.composite.controller.Command;
import by.training.composite.controller.SortLexemeInSentence;
import by.training.composite.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Sort lexeme in sentences button.
 */
public class SortLexemeInSentencesButton extends MenuEntry {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Component.
     */
    private Component component;

    /**
     * Constructor.
     *
     * @param titleNew     name menu entry.
     * @param componentNew component.
     */
    SortLexemeInSentencesButton(final String titleNew,
                                final Component componentNew) {
        super(titleNew);
        component = componentNew;
    }

    /**
     * Runner.
     */
    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        char choice;

        Printer.printInfo(ResourceManager.INSTANCE.getString("input"));
        try {
            choice = reader.readLine().charAt(0);
            Command command = new SortLexemeInSentence(component, choice);
            command.execute();
        } catch (IOException e) {
            LOGGER.warn("Incorrect value.", e);
        }
    }
}
