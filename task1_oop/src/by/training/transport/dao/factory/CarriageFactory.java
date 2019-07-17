package by.training.transport.dao.factory;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.dao.factory.exception.CarriageValidationException;

public interface CarriageFactory {
    /**
     * @param line incoming data.
     * @return create passenger carriage.
     * @throws CarriageValidationException if incorrect data for carriage.
     */
    PassengerCarriage createCarriage(String... line)
            throws CarriageValidationException;
}
