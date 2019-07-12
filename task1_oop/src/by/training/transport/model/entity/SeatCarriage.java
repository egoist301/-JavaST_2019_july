package by.training.transport.model.entity;

import java.util.Objects;

public class SeatCarriage extends PassengerCarriage {
    public static final int DEFAULT_NUMBER_OF_PLACES = 62;
    private int numberOfSeats;

    public SeatCarriage(final int numberOfPassengersNew,
                        final int numberOfLuggageNew,
                        final int numberOfSeatsNew) {
        super(numberOfPassengersNew, numberOfLuggageNew);
        numberOfSeats = numberOfSeatsNew;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(final int numberOfSeatsNew) {
        numberOfSeats = numberOfSeatsNew;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNumberOfSeats());
    }

    @Override
    public String toString() {
        return "SeatCarriage{" + super.toString()
                + "numberOfSeats=" + numberOfSeats + '}';
    }
}
