package by.training.transport.model.specification;

import by.training.transport.model.entity.PassengerCarriage;

public class FindSpecificationByPassengersRange implements FindSpecification {
    /**
     * Minimum number of passengers.
     */
    private int minPassengers;
    /**
     * Maximum number of passengers.
     */
    private int maxPassengers;

    /**
     * @param minPassengersNew minimum number of passengers.
     * @param maxPassengersNew maximum number of passengers.
     */
    public FindSpecificationByPassengersRange(final int minPassengersNew,
                                              final int maxPassengersNew) {
        minPassengers = minPassengersNew;
        maxPassengers = maxPassengersNew;
    }

    /**
     * @param passengerCarriageNew carriage for comparison.
     * @return equals or not equals.
     */
    @Override
    public boolean find(final PassengerCarriage passengerCarriageNew) {
        return passengerCarriageNew.getNumberOfPassengers() < maxPassengers
                && passengerCarriageNew.getNumberOfPassengers() > minPassengers;
    }
}
