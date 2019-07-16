package by.training.transport.dao.repository;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.dao.repository.exception.IllegalSpecificationException;
import by.training.transport.dao.repository.specification.findspecification.FindSpecification;
import by.training.transport.dao.repository.specification.sortspecification.SortSpecification;
import by.training.transport.dao.repository.specification.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Train implements TrainRepository {
    /**
     * List of carriages.
     */
    private List<PassengerCarriage> listTrain;

    /**
     * Default constructor.
     */
    public Train() {
        listTrain = new ArrayList<>();
    }

    /**
     * @param listTrainNew constructor with parameter (list).
     */
    public Train(final List<PassengerCarriage> listTrainNew) {
        listTrain = listTrainNew;
    }


    /**
     * @param passengerCarriageNew adding carriage.
     */
    @Override
    public void add(final PassengerCarriage passengerCarriageNew) {
        listTrain.add(passengerCarriageNew);
    }

    /**
     * @param specificationNew specification for sorting or searching.
     * @return list of passengers.
     * @throws IllegalSpecificationException incorrect specification.
     */
    @Override
    public List<PassengerCarriage> query(final Specification specificationNew)
            throws IllegalSpecificationException {
        List<PassengerCarriage> result;
        if (specificationNew instanceof FindSpecification) {
            result = new ArrayList<>();
            for (PassengerCarriage passengerCarriage : listTrain) {
                if (((FindSpecification) specificationNew)
                        .find(passengerCarriage)) {
                    result.add(passengerCarriage);
                }
            }
        } else if (specificationNew instanceof SortSpecification) {
            result = listTrain;
            result.sort(((SortSpecification) specificationNew)
                    .specifiedComparator());
        } else {
            throw new IllegalSpecificationException("This object is not a "
                    + "required specification.");
        }
        return result;
    }

    /**
     * @return length of listTrain.
     */
    @Override
    public int size() {
        return listTrain.size();
    }

    /**
     * @return isEmpty.
     */
    @Override
    public boolean isEmpty() {
        return listTrain.isEmpty();
    }

    /**
     * @param index collection index.
     * @return carriage.
     */
    @Override
    public PassengerCarriage get(final int index) {
        return listTrain.get(index);
    }

    /**
     * @param passengerCarriageNew carriage.
     * @param index                collection index.
     */
    @Override
    public void set(final PassengerCarriage passengerCarriageNew,
                    final int index) {
        listTrain.set(index, passengerCarriageNew);
    }

    /**
     * @return all carriages.
     */
    @Override
    public List<PassengerCarriage> getAll() {
        return listTrain;
    }

    /**
     * @param oNew any object.
     * @return equal or not equal.
     */
    @Override
    public boolean equals(final Object oNew) {
        if (this == oNew) {
            return true;
        }
        if (!(oNew instanceof Train)) {
            return false;
        }
        Train train1 = (Train) oNew;
        return Objects.equals(listTrain, train1.listTrain);
    }

    /**
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(listTrain);
    }

    /**
     * @return train description.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (PassengerCarriage passengerCarriage : listTrain){
            stringBuilder.append(passengerCarriage + "\n");
        }
        return stringBuilder.toString();
    }
}
