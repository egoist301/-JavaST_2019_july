package by.training.transport.bean.factory;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.bean.factory.exception.CarriageValidationException;

public interface CarriageFactory {
    /**
     * @param line incoming data.
     * @return create passenger carriage.
     * @throws CarriageValidationException if incorrect data for carriage.
     */
    PassengerCarriage createCarriage(final String... line)
            throws CarriageValidationException;
}
