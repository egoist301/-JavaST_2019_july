package by.training.transport.controller.findmenubutton;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.dao.repository.Train;
import by.training.transport.dao.repository.exception.IllegalSpecificationException;
import by.training.transport.dao.repository.specification.findspecification.FindSpecificationByPassengers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindByPassengers {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @param numberOfPassengers number of passengers.
     * @return list.
     */
    public List<PassengerCarriage> execute(final int numberOfPassengers) {
        List<PassengerCarriage> list;
        try {
            list = Train.getTrain().query(
                    new FindSpecificationByPassengers(numberOfPassengers));
        } catch (IllegalSpecificationException ex) {
            LOGGER.info(ex);
            throw new RuntimeException(ex);
        }
        return list;
    }
}
