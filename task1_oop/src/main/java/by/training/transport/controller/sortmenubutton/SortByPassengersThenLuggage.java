package by.training.transport.controller.sortmenubutton;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.dao.repository.Train;
import by.training.transport.dao.repository.exception.IllegalSpecificationException;
import by.training.transport.dao.repository.specification.sortspecification.SortSpecificationByPassengersThenByLuggage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SortByPassengersThenLuggage {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @return list.
     */
    public List<PassengerCarriage> sort() {
        List<PassengerCarriage> list;
        try {
            list = Train.getTrain().query(
                    new SortSpecificationByPassengersThenByLuggage());
        } catch (IllegalSpecificationException ex) {
            LOGGER.fatal(ex);
            throw new RuntimeException(ex);
        }
        return list;
    }
}
