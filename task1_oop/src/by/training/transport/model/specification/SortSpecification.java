package by.training.transport.model.specification;

import by.training.transport.model.entity.PassengerCarriage;

import java.util.Comparator;

public interface SortSpecification extends Specification {
    /**
     * @return comparator for Passenger carriage.
     */
    Comparator<PassengerCarriage> specifiedComparator();
}
