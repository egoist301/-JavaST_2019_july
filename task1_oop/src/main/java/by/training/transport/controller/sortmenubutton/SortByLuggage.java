package by.training.transport.controller.sortmenubutton;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.dao.repository.Train;
import by.training.transport.dao.repository.exception.IllegalSpecificationException;
import by.training.transport.dao.repository.specification.sortspecification.SortSpecificationByLuggage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SortByLuggage {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @return list.
     */
    public List<PassengerCarriage> execute() {
        List<PassengerCarriage> list;
        try {
            list = Train.getTrain().query(new SortSpecificationByLuggage());
        } catch (IllegalSpecificationException ex) {
            LOGGER.fatal(ex);
            throw new RuntimeException(ex);
        }
        return list;
    }
}
