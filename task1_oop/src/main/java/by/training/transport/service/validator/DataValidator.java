package by.training.transport.service.validator;

import by.training.transport.bean.entity.Coupe;
import by.training.transport.bean.entity.Placecart;
import by.training.transport.bean.entity.SeatCarriage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DataValidator {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
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
     * Data length.
     */
    private static final int DATA_LENGTH = 4;

    private DataValidator() {
    }

    private static boolean isInteger(final String line) {
        try {
            Integer.parseInt(line);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Incorrect value.", ex);
            return false;
        }
        return true;
    }

    private static boolean isNumberOfPassengers(final String line,
                                                final int maximumPlaces) {
        int valid = Integer.parseInt(line);
        return isInteger(line)
                && valid >= 0
                && valid <= maximumPlaces;
    }

    private static boolean isNumberOfLuggage(final String line) {
        return isInteger(line)
                && Integer.parseInt(line) >= 0;
    }

    private static boolean isNumberOfCompartments(final String line) {
        return isInteger(line)
                && Integer.parseInt(line) >= 0
                && Integer.parseInt(line)
                <= Placecart.DEFAULR_NUMBER_OF_COMPARTMENTS;
    }

    private static boolean isNumberOfSeats(final String line) {
        return isInteger(line)
                && Integer.parseInt(line) >= 0
                && Integer.parseInt(line)
                <= SeatCarriage.DEFAULT_NUMBER_OF_PLACES;
    }

    private static boolean isNumberOfCoupe(final String line) {
        return isInteger(line)
                && Integer.parseInt(line) >= 0
                && Integer.parseInt(line) <= Coupe.DEFAULT_NUMBER_OF_COUPE;
    }

    /**
     * @param line incoming line.
     * @return correct values or not.
     */
    public static boolean isValidCoupe(final String[] line) {
        return line.length == DATA_LENGTH
                && isNumberOfPassengers(line[FIRST_VALUE],
                Coupe.DEFAULT_NUMBER_OF_PLACES)
                && isNumberOfCoupe(line[THIRD_VALUE])
                && isNumberOfLuggage(line[SECOND_VALUE]);
    }

    /**
     * @param line incoming line.
     * @return correct values or not.
     */
    public static boolean isValidPlacecart(final String[] line) {
        return line.length == DATA_LENGTH
                && isNumberOfPassengers(line[FIRST_VALUE],
                Placecart.DEFAULT_NUMBER_OF_PLACES)
                && isNumberOfLuggage(line[SECOND_VALUE])
                && isNumberOfCompartments(line[THIRD_VALUE]);
    }

    /**
     * @param line incoming line.
     * @return correct values or not.
     */
    public static boolean isValidSeatCarriage(final String[] line) {
        return line.length == DATA_LENGTH
                && isNumberOfPassengers(line[FIRST_VALUE],
                SeatCarriage.DEFAULT_NUMBER_OF_PLACES)
                && isNumberOfLuggage(line[SECOND_VALUE])
                && isNumberOfSeats(line[THIRD_VALUE]);
    }
}
