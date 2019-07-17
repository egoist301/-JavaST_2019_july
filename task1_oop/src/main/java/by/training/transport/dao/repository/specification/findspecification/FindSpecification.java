package by.training.transport.dao.repository.specification.findspecification;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.dao.repository.specification.Specification;

public interface FindSpecification extends Specification {
    /**
     * @param passengerCarriageNew carriage for comparison.
     * @return equals or not equals.
     */
    boolean find(PassengerCarriage passengerCarriageNew);
}
