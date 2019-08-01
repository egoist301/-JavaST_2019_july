package by.training.transport.bean.entity;

import by.training.transport.dao.repository.Train;

import java.util.Objects;

public abstract class PassengerCarriage {
    /**
     * Number of passengers.
     */
    private int numberOfPassengers;
    /**
     * Number of luggage.
     */
    private int numberOfLuggage;
    /**
     * Id variable.
     */
    private static long tempId;
    /**
     * Id of passengerCarriage.
     */
    private long passengerCarriageId = ++tempId;

    /**
     * Subscriber.
     */
    private Train train;

    /**
     * @param numberOfPassengersNew number of passengers.
     * @param numberOfLuggageNew    number of luggage.
     */
    public PassengerCarriage(final int numberOfPassengersNew,
                             final int numberOfLuggageNew) {
        this.numberOfPassengers = numberOfPassengersNew;
        this.numberOfLuggage = numberOfLuggageNew;
    }

    /**
     * @return ID of passengerCarriage.
     */
    public long getPassengerCarriageId() {
        return passengerCarriageId;
    }

    /**
     * @return number of passengers.
     */
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    /**
     * @param numberOfPassengersNew number of passengers.
     */
    public void setNumberOfPassengers(final int numberOfPassengersNew) {
        this.numberOfPassengers = numberOfPassengersNew;
        notifyObservers();
    }

    /**
     * @return number of luggage.
     */
    public int getNumberOfLuggage() {
        return numberOfLuggage;
    }

    /**
     * @param numberOfLuggageNew number of luggage.
     */
    public void setNumberOfLuggage(final int numberOfLuggageNew) {
        this.numberOfLuggage = numberOfLuggageNew;
        notifyObservers();
    }

    /**
     * @param trainNew train - observer.
     */
    public void addObserver(final Train trainNew) {
        train = trainNew;
        train.add(this);
    }

    private void notifyObservers() {
        train.update();
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
        if (!(oNew instanceof PassengerCarriage)) {
            return false;
        }
        PassengerCarriage that = (PassengerCarriage) oNew;
        return getNumberOfPassengers() == that.getNumberOfPassengers()
                && getNumberOfLuggage() == that.getNumberOfLuggage();
    }

    /**
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getNumberOfPassengers(), getNumberOfLuggage());
    }

    /**
     * @return car description.
     */
    @Override
    public String toString() {
        return "Id='" + passengerCarriageId + '\''
                + ", numberOfPassengers=" + numberOfPassengers
                + ", numberOfLuggage=" + numberOfLuggage;
    }
}
