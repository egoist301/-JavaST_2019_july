package by.training.transport.model.entity;

import java.util.Objects;

public abstract class PassengerCarriage {
    private int numberOfPassengers;
    private int numberOfLuggage;
    private static long tempId;
    private final long id = ++tempId;

    public PassengerCarriage(final int numberOfPassengersNew,
                             final int numberOfLuggageNew) {
        this.numberOfPassengers = numberOfPassengersNew;
        this.numberOfLuggage = numberOfLuggageNew;
    }

    public long getId() {
        return id;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(final int numberOfPassengersNew) {
        this.numberOfPassengers = numberOfPassengersNew;
    }

    public int getNumberOfLuggage() {
        return numberOfLuggage;
    }

    public void setNumberOfLuggage(final int numberOfLuggageNew) {
        this.numberOfLuggage = numberOfLuggageNew;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PassengerCarriage)) {
            return false;
        }
        PassengerCarriage that = (PassengerCarriage) o;
        return getNumberOfPassengers() == that.getNumberOfPassengers()
                && getNumberOfLuggage() == that.getNumberOfLuggage();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumberOfPassengers(), getNumberOfLuggage());
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' + ", numberOfPassengers=" + numberOfPassengers
                + ", numberOfLuggage=" + numberOfLuggage;
    }
}
