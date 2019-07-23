package by.training.transport.controller.findmenubutton;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.dao.repository.Train;
import by.training.transport.dao.repository.exception.IllegalSpecificationException;
import by.training.transport.dao.repository.specification.findspecification.FindSpecificationByLuggage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindByLuggage {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @param numberOfLuggage number of luggage.
     * @return list.
     */
    public List<PassengerCarriage> execute(final int numberOfLuggage) {
        List<PassengerCarriage> list;
        try {
            list = Train.getTrain().query(
                    new FindSpecificationByLuggage(numberOfLuggage));
        } catch (IllegalSpecificationException ex) {
            LOGGER.info(ex);
            throw new RuntimeException(ex);
        }
        return list;
    }
}
