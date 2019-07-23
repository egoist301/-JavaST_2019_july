package by.training.transport.view.menu;

import by.training.transport.dao.repository.Train;
import by.training.transport.view.Printer;

public class PrintTrain extends MenuEntry {

    /**
     * @param titleNew name menu entry.
     */
    public PrintTrain(final String titleNew) {
        super(titleNew);
    }

    /**
     * Runner.
     */
    @Override
    public void run() {
        Printer.execute(Train.getTrain());
    }
}
