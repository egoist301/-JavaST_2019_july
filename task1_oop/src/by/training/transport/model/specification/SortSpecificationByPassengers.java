package by.training.transport.model.specification;

import by.training.transport.model.entity.PassengerCarriage;

import java.util.Comparator;

public class SortSpecificationByPassengers implements SortSpecification {

    /**
     * @return comparator for Passenger carriage by number of passengers.
     */
    @Override
    public Comparator<PassengerCarriage> specifiedComparator() {
        return Comparator.comparingInt(PassengerCarriage
                ::getNumberOfPassengers);
    }
}
