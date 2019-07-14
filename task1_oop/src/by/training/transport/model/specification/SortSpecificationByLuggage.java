package by.training.transport.model.specification;

import by.training.transport.model.entity.PassengerCarriage;

import java.util.Comparator;

public class SortSpecificationByLuggage implements SortSpecification {
    /**
     * @return comparator for Passenger carriage by number of luggage.
     */
    @Override
    public Comparator<PassengerCarriage> specifiedComparator() {
        return Comparator.comparingInt(PassengerCarriage::getNumberOfLuggage);
    }
}
