package by.training.transport.model.entity;

import java.util.Objects;

public class Coupe extends PassengerCarriage {
    public final static int DEFAULT_NUMBER_OF_PLACES = 36;
    private int numberOfCoupe;

    public Coupe(final int numberOfPassengersNew, final int numberOfLuggageNew,
                 final int numberOfCoupeNew) {
        super(numberOfPassengersNew, numberOfLuggageNew);
        numberOfCoupe = numberOfCoupeNew;
    }

    public int getNumberOfCoupe() {
        return numberOfCoupe;
    }

    public void setNumberOfCoupe(final int numberOfCoupeNew) {
        numberOfCoupe = numberOfCoupeNew;
    }

    @Override
    public boolean equals(final Object oNew) {
        if (this == oNew) {
            return true;
        }
        if (!(oNew instanceof Coupe)) {
            return false;
        }
        if (!super.equals(oNew)) {
            return false;
        }
        Coupe coupe = (Coupe) oNew;
        return getNumberOfCoupe() == coupe.getNumberOfCoupe();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNumberOfCoupe());
    }

    @Override
    public String toString() {
        return "Coupe{" + super.toString()
                + "numberOfCoupe=" + numberOfCoupe + '}';
    }
}
