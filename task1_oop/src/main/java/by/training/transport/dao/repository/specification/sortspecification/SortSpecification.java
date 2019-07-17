package by.training.transport.dao.repository.specification.sortspecification;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.dao.repository.specification.Specification;

import java.util.Comparator;

public interface SortSpecification extends Specification {
    /**
     * @return comparator for Passenger carriage.
     */
    Comparator<PassengerCarriage> specifiedComparator();
}
