package by.training.transport.bean.factory;

import by.training.transport.bean.entity.Coupe;
import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.bean.factory.exception.CarriageValidationException;
import by.training.transport.service.validator.DataValidator;

import java.util.Arrays;

public final class CoupeFactory implements CarriageFactory {
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
    private static final CoupeFactory COUPE_FACTORY = new CoupeFactory();

    private CoupeFactory() {
    }

    /**
     * @return Coupe factory.
     */
    public static CoupeFactory getFactory() {
        return COUPE_FACTORY;
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
        int numberOfCoupe;
        int numberOfLuggage;
        if (DataValidator.isValidCoupe(line)) {
            numberOfPassengers = Integer.parseInt(line[FIRST_VALUE]);
            numberOfLuggage = Integer.parseInt(line[SECOND_VALUE]);
            numberOfCoupe = Integer.parseInt(line[THIRD_VALUE]);
        } else {
            throw new CarriageValidationException(MESSAGE
                    + Arrays.toString(line));
        }
        return new Coupe(numberOfPassengers, numberOfLuggage, numberOfCoupe);
    }
}
