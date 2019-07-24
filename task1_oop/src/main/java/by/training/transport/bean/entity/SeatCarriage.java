package by.training.transport.bean.entity;

import java.util.Objects;

public class SeatCarriage extends PassengerCarriage {
    /**
     * Number of places in seat carriage.
     */
    public static final int DEFAULT_NUMBER_OF_PLACES = 62;
    /**
     * Number of seats.
     */
    private int numberOfSeats;

    /**
     * @param numberOfPassengersNew number of passengers.
     * @param numberOfLuggageNew    number of luggage.
     * @param numberOfSeatsNew      number of seats.
     */
    public SeatCarriage(final int numberOfPassengersNew,
                        final int numberOfLuggageNew,
                        final int numberOfSeatsNew) {
        super(numberOfPassengersNew, numberOfLuggageNew);
        numberOfSeats = numberOfSeatsNew;
    }

    /**
     * @return number of seats.
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * @param numberOfSeatsNew number of seats.
     */
    public void setNumberOfSeats(final int numberOfSeatsNew) {
        numberOfSeats = numberOfSeatsNew;
    }

    /**
     * @param oNew any object.
     * @return equal or not equal.
     */
    @Override
    public boolean equals(final Object oNew) {
        if (this == oNew) {
            return true;
        }
        if (!(oNew instanceof SeatCarriage)) {
            return false;
        }
        if (!super.equals(oNew)) {
            return false;
        }
        SeatCarriage that = (SeatCarriage) oNew;
        return getNumberOfSeats() == that.getNumberOfSeats();
    }

    /**
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNumberOfSeats());
    }

    /**
     * @return car description.
     */
    @Override
    public String toString() {
        return "SeatCarriage{" + super.toString()
                + ", numberOfSeats=" + numberOfSeats + '}';
    }
}
