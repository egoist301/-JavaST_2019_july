package by.training.transport.controller.findmenubutton;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.dao.repository.Train;
import by.training.transport.dao.repository.exception.IllegalSpecificationException;
import by.training.transport.dao.repository.specification.findspecification.FindSpecificationById;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindById {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @param id id.
     * @return list.
     */
    public List<PassengerCarriage> find(final long id) {
        List<PassengerCarriage> list;
        try {
            list = Train.getTrain().query(new FindSpecificationById(id));
        } catch (IllegalSpecificationException ex) {
            LOGGER.fatal(ex);
            throw new RuntimeException(ex);
        }
        return list;
    }
}
