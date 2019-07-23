package by.training.transport.controller.findmenubutton;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.dao.repository.Train;
import by.training.transport.dao.repository.exception.IllegalSpecificationException;
import by.training.transport.dao.repository.specification.findspecification.FindSpecificationByPassengersRange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindByPassengersRange {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @param numberOfPassengersMIN number of passengers min.
     * @param numberOfPassengersMAX number of passengers max.
     * @return list.
     */
    public List<PassengerCarriage> execute(final int numberOfPassengersMIN,
                                           final int numberOfPassengersMAX) {
        List<PassengerCarriage> list;
        try {
            list = Train.getTrain().query(
                    new FindSpecificationByPassengersRange(
                            numberOfPassengersMIN, numberOfPassengersMAX));
        } catch (IllegalSpecificationException ex) {
            LOGGER.info(ex);
            throw new RuntimeException(ex);
        }
        return list;
    }
}
