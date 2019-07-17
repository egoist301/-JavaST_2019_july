package by.training.transport.dao.repository.specification.findspecification;

import by.training.transport.bean.entity.PassengerCarriage;

public class FindSpecificationByPassengers implements FindSpecification {
    /**
     * Number of passengers.
     */
    private int numberOfPassengers;

    /**
     * @param numberOfPassengersNew number of passengers.
     */
    public FindSpecificationByPassengers(final int numberOfPassengersNew) {
        numberOfPassengers = numberOfPassengersNew;
    }

    /**
     * @param passengerCarriageNew carriage for comparison .
     * @return equals or not equals.
     */
    @Override
    public boolean find(final PassengerCarriage passengerCarriageNew) {
        return passengerCarriageNew.getNumberOfPassengers()
                == numberOfPassengers;
    }
}
