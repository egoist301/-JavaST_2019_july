package by.training.transport.model.specification;

import by.training.transport.model.entity.PassengerCarriage;

public interface FindSpecification extends Specification {
    /**
     * @param passengerCarriageNew carriage for comparison.
     * @return equals or not equals.
     */
    boolean find(PassengerCarriage passengerCarriageNew);
}
