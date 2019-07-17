package by.training.transport.dao.factory;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.bean.entity.SeatCarriage;
import by.training.transport.dao.factory.exception.CarriageValidationException;
import by.training.transport.service.validator.DataValidator;

import java.util.Arrays;

public final class SeatCarriageFactory implements CarriageFactory {
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
    private static final SeatCarriageFactory SEAT_CARRIAGE_FACTORY
            = new SeatCarriageFactory();

    private SeatCarriageFactory() {
    }

    /**
     * @return Seat carriage factory.
     */
    public static SeatCarriageFactory getFactory() {
        return SEAT_CARRIAGE_FACTORY;
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
        int numberOfSeats;
        int numberOfLuggage;
        if (DataValidator.isValidSeatCarriage(line)) {
            numberOfPassengers = Integer.parseInt(line[FIRST_VALUE]);
            numberOfLuggage = Integer.parseInt(line[SECOND_VALUE]);
            numberOfSeats = Integer.parseInt(line[THIRD_VALUE]);
        } else {
            throw new CarriageValidationException(MESSAGE
                    + Arrays.toString(line));
        }
        return new SeatCarriage(numberOfPassengers,
                numberOfLuggage,
                numberOfSeats);
    }
}
