package by.training.transport.model.specification;

import by.training.transport.model.entity.PassengerCarriage;

public class FindSpecificationByLuggage implements FindSpecification {
    /**
     * Number of luggage.
     */
    private int numberOfLuggage;

    /**
     * @param numberOfLuggageNew number of luggage.
     */
    public FindSpecificationByLuggage(final int numberOfLuggageNew) {
        numberOfLuggage = numberOfLuggageNew;
    }

    /**
     * @param passengerCarriageNew carriage for comparison.
     * @return equals or not equals.
     */
    @Override
    public boolean find(final PassengerCarriage passengerCarriageNew) {
        return passengerCarriageNew.getNumberOfLuggage() == numberOfLuggage;
    }
}
