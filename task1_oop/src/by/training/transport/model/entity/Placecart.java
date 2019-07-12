package by.training.transport.model.entity;

import java.util.Objects;

public class Placecart extends PassengerCarriage {
    public static final int DEFAULT_NUMBER_OF_PLACES = 54;
    private int numberOfCompartments;

    public Placecart(final int numberOfPassengersNew,
                     final int numberOfLuggageNew,
                     final int numberOfCompartmentsNew) {
        super(numberOfPassengersNew, numberOfLuggageNew);
        numberOfCompartments = numberOfCompartmentsNew;
    }

    public int getNumberOfCompartments() {
        return numberOfCompartments;
    }

    public void setNumberOfCompartments(final int numberOfCompartmentsNew) {
        numberOfCompartments = numberOfCompartmentsNew;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNumberOfCompartments());
    }

    @Override
    public String toString() {
        return "Placecart{" + super.toString()
                + "numberOfCompartments=" + numberOfCompartments + '}';
    }
}

