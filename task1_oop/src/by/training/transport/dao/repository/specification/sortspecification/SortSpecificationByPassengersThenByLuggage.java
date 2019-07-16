package by.training.transport.dao.repository.specification.sortspecification;

import by.training.transport.bean.entity.PassengerCarriage;

import java.util.Comparator;

public class SortSpecificationByPassengersThenByLuggage
        implements SortSpecification {
    /**
     * @return comparator for Passenger carriage by passenger then by luggage.
     */
    @Override
    public Comparator<PassengerCarriage> specifiedComparator() {
        Comparator<PassengerCarriage> passengerCarriageComparator
                = new SortSpecificationByPassengers().specifiedComparator();
        Comparator<PassengerCarriage> luggageCarriageComparator
                = new SortSpecificationByLuggage().specifiedComparator();
        return passengerCarriageComparator
                .thenComparing(luggageCarriageComparator);
    }
}
