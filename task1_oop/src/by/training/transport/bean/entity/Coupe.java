package by.training.transport.bean.entity;

import java.util.Objects;

public class Coupe extends PassengerCarriage {
    /**
     * Number of places in coupe.
     */
    public static final int DEFAULT_NUMBER_OF_PLACES = 36;
    /**
     * Number of coupe.
     */
    private int numberOfCoupe;

    /**
     * @param numberOfPassengersNew number of passengers.
     * @param numberOfLuggageNew number of luggage.
     * @param numberOfCoupeNew number of coupe.
     */
    public Coupe(final int numberOfPassengersNew, final int numberOfLuggageNew,
                 final int numberOfCoupeNew) {
        super(numberOfPassengersNew, numberOfLuggageNew);
        numberOfCoupe = numberOfCoupeNew;
    }

    /**
     * @return number of coupe.
     */
    public int getNumberOfCoupe() {
        return numberOfCoupe;
    }

    /**
     * @param numberOfCoupeNew number of coupe.
     */
    public void setNumberOfCoupe(final int numberOfCoupeNew) {
        numberOfCoupe = numberOfCoupeNew;
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
        if (!(oNew instanceof Coupe)) {
            return false;
        }
        if (!super.equals(oNew)) {
            return false;
        }
        Coupe coupe = (Coupe) oNew;
        return getNumberOfCoupe() == coupe.getNumberOfCoupe();
    }

    /**
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNumberOfCoupe());
    }

    /**
     * @return car description.
     */
    @Override
    public String toString() {
        return "Coupe{" + super.toString()
                + "numberOfCoupe=" + numberOfCoupe + '}';
    }
}
