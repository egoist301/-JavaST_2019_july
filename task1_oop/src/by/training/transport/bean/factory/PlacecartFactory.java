package by.training.transport.bean.factory;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.bean.entity.Placecart;
import by.training.transport.bean.factory.exception.CarriageValidationException;
import by.training.transport.service.validator.DataValidator;

import java.util.Arrays;

public final class PlacecartFactory implements CarriageFactory {
    /**
     * First value in array.
     */
    private static final int FIRST_VALUE = 1;
    /**
     * Second value in array.
     */
    private static final int SECOND_VALUE = 2;
    /**
     * Third value in array.
     */
    private static final int THIRD_VALUE = 3;
    /**
     * Message for exception.
     */
    private static final String MESSAGE = "Incorrect data.";
    /**
     * Singleton.
     */
    private static final PlacecartFactory PLACECART_FACTORY
            = new PlacecartFactory();

    private PlacecartFactory() {
    }

    /**
     * @return placecart factory.
     */
    public static PlacecartFactory getFactory() {
        return PLACECART_FACTORY;
    }

    /**
     * @param line incoming data.
     * @return create passenger carriage.
     * @throws CarriageValidationException if incorrect data for carriage.
     */
    @Override
    public PassengerCarriage createCarriage(final String... line)
            throws CarriageValidationException {
        int numberOfPassengers;
        int numberOfCompartments;
        int numberOfLuggage;
        if (DataValidator.isValidPlacecart(line)) {
            numberOfPassengers = Integer.parseInt(line[FIRST_VALUE]);
            numberOfLuggage = Integer.parseInt(line[SECOND_VALUE]);
            numberOfCompartments = Integer.parseInt(line[THIRD_VALUE]);
        } else {
            throw new CarriageValidationException(MESSAGE
                    + Arrays.toString(line));
        }
        return new Placecart(numberOfPassengers,
                numberOfLuggage,
                numberOfCompartments);
    }
}
