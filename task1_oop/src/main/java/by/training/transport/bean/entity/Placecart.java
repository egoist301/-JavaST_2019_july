package by.training.transport.bean.entity;

import java.util.Objects;

public class Placecart extends PassengerCarriage {
    /**
     * Number of places in reserved seat.
     */
    public static final int DEFAULT_NUMBER_OF_PLACES = 54;
    /**
     * Number of compartments fix.
     */
    public static final int DEFAULR_NUMBER_OF_COMPARTMENTS = 18;
    /**
     * Number of compartments.
     */
    private int numberOfCompartments;

    /**
     * @param numberOfPassengersNew   number of passengers.
     * @param numberOfLuggageNew      number of luggage.
     * @param numberOfCompartmentsNew number of compartments.
     */
    public Placecart(final int numberOfPassengersNew,
                     final int numberOfLuggageNew,
                     final int numberOfCompartmentsNew) {
        super(numberOfPassengersNew, numberOfLuggageNew);
        numberOfCompartments = numberOfCompartmentsNew;
    }

    /**
     * @return number of compartments.
     */
    public int getNumberOfCompartments() {
        return numberOfCompartments;
    }

    /**
     * @param numberOfCompartmentsNew number of compartments.
     */
    public void setNumberOfCompartments(final int numberOfCompartmentsNew) {
        numberOfCompartments = numberOfCompartmentsNew;
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
        if (!(oNew instanceof Placecart)) {
            return false;
        }
        if (!super.equals(oNew)) {
            return false;
        }
        Placecart placecart = (Placecart) oNew;
        return getNumberOfCompartments() == placecart.getNumberOfCompartments();
    }

    /**
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNumberOfCompartments());
    }

    /**
     * @return car description.
     */
    @Override
    public String toString() {
        return "Placecart{" + super.toString()
                + ", numberOfCompartments=" + numberOfCompartments + '}';
    }
}

