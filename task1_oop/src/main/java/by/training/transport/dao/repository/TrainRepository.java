package by.training.transport.dao.repository;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.dao.repository.exception.IllegalSpecificationException;
import by.training.transport.dao.repository.specification.Specification;

import java.util.List;

public interface TrainRepository {
    /**
     * @param passengerCarriageNew add passenger carriage in repository.
     */
    void add(PassengerCarriage passengerCarriageNew);

    /**
     * @param specificationNew specification for sorting or searching.
     * @return list of passengers.
     * @throws IllegalSpecificationException incorrect specification.
     */
    List<PassengerCarriage> query(Specification specificationNew)
            throws IllegalSpecificationException;

    /**
     * @return size.
     */
    int size();

    /**
     * clear repository.
     */
    void clear();

    /**
     * @return isEmpty.
     */
    boolean isEmpty();

    /**
     * @param index index of passenger carriage.
     * @return passenger carriage.
     */
    PassengerCarriage get(int index);

    /**
     * @param passengerCarriageNew passenger carriage.
     * @param index                index of passenger carriage.
     */
    void set(PassengerCarriage passengerCarriageNew, int index);

    /**
     * @return list of passenger carriage.
     */
    List<PassengerCarriage> getAll();
}
