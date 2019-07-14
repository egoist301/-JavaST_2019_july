package by.training.transport.model.specification;

import by.training.transport.model.entity.PassengerCarriage;

public class FindSpecificationById implements FindSpecification {
    /**
     * Id of passenger carriage.
     */
    private long passengerCarriageId;

    /**
     * @param passengerCarriageIdNew Id of passenger carriage.
     */
    public FindSpecificationById(final long passengerCarriageIdNew) {
        passengerCarriageId = passengerCarriageIdNew;
    }

    /**
     * @param passengerCarriageNew carriage for comparison.
     * @return equals or not equals.
     */
    @Override
    public boolean find(final PassengerCarriage passengerCarriageNew) {
        return passengerCarriageNew.getPassengerCarriageId()
                == passengerCarriageId;
    }
}
