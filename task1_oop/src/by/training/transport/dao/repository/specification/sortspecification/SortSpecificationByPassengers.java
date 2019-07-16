package by.training.transport.dao.repository.specification.sortspecification;

import by.training.transport.bean.entity.PassengerCarriage;

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
