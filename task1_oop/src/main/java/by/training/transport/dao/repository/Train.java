package by.training.transport.dao.repository;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.dao.repository.exception.IllegalSpecificationException;
import by.training.transport.dao.repository.specification.Specification;
import by.training.transport.dao.repository.specification.findspecification.FindSpecification;
import by.training.transport.dao.repository.specification.sortspecification.SortSpecification;
import by.training.transport.service.TrainService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Train implements TrainRepository, Observer {
    /**
     * Number of passengers in train.
     */
    private int numberOfPassengers;
    /**
     * Number of luggage in train.
     */
    private int numberOLuggage;
    /**
     * Singleton.
     */
    private static final Train TRAIN = new Train();
    /**
     * List of carriages.
     */
    private List<PassengerCarriage> listTrain;

    /**
     * Default constructor.
     */
    private Train() {
        listTrain = new ArrayList<>();
    }

    /**
     * @return TRAIN(Singleton).
     */
    public static Train getTrain() {
        return TRAIN;
    }

    /**
     * @return number of passengers in train.
     */
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    /**
     * @return number of luggage in train.
     */
    public int getNumberOfLuggage() {
        return numberOLuggage;
    }

    /**
     * @param passengerCarriageNew adding carriage.
     */
    @Override
    public void add(final PassengerCarriage passengerCarriageNew) {
        listTrain.add(passengerCarriageNew);
        numberOfPassengers += passengerCarriageNew.getNumberOfPassengers();
        numberOLuggage += passengerCarriageNew.getNumberOfLuggage();
    }

    /**
     * clear listTrain.
     */
    @Override
    public void clear() {
        listTrain.clear();
        update();
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
     * @return TRAIN description.
     */
    @Override
    public String toString() {
        String info = "";
        for (PassengerCarriage passengerCarriage : listTrain) {
            info = info.concat(passengerCarriage + "\n");
        }
        return info;
    }

    /**
     * Update.
     */
    @Override
    public void update() {
        numberOLuggage = TrainService.countingTheNumberOfLuggage(this);
        numberOfPassengers = TrainService.countingTheNumberOfPassengers(this);
    }
}
