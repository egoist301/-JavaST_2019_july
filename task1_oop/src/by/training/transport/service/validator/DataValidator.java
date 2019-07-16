package by.training.transport.service.validator;

import by.training.transport.bean.entity.Coupe;
import by.training.transport.bean.entity.Placecart;
import by.training.transport.bean.entity.SeatCarriage;

public final class DataValidator {
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

    private DataValidator() {
    }

    private static boolean isNumberOfPassengers(final String line,
                                                final int maximumPlaces) {
        int valid = Integer.parseInt(line);
        return valid >= 0
                && valid <= maximumPlaces;
    }

    private static boolean isNumberOfLuggage(final String line) {
        return Integer.parseInt(line) >= 0;
    }

    private static boolean isNumberOfCompartments(final String line) {
        return Integer.parseInt(line) >= 0;
    }

    private static boolean isNumberOfSeats(final String line) {
        return Integer.parseInt(line) >= 0;
    }

    private static boolean isNumberOfCoupe(final String line) {
        return Integer.parseInt(line) >= 0;
    }

    /**
     * @param line incoming line.
     * @return correct values or not.
     */
    public static boolean isValidCoupe(final String[] line) {
        return isNumberOfPassengers(line[FIRST_VALUE],
                Coupe.DEFAULT_NUMBER_OF_PLACES)
                && isNumberOfCoupe(line[SECOND_VALUE])
                && isNumberOfLuggage(line[THIRD_VALUE]);
    }

    /**
     * @param line incoming line.
     * @return correct values or not.
     */
    public static boolean isValidPlacecart(final String[] line) {
        return isNumberOfPassengers(line[FIRST_VALUE],
                Placecart.DEFAULT_NUMBER_OF_PLACES)
                && isNumberOfLuggage(line[SECOND_VALUE])
                && isNumberOfCompartments(line[THIRD_VALUE]);
    }

    /**
     * @param line incoming line.
     * @return correct values or not.
     */
    public static boolean isValidSeatCarriage(final String[] line) {
        return isNumberOfPassengers(line[FIRST_VALUE],
                SeatCarriage.DEFAULT_NUMBER_OF_PLACES)
                && isNumberOfLuggage(line[SECOND_VALUE])
                && isNumberOfSeats(line[THIRD_VALUE]);
    }
}
