package by.training.transport.model;

import by.training.transport.model.entity.PassengerCarriage;
import by.training.transport.model.specification.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Train implements TrainRepository {
    private List<PassengerCarriage> train;

    public Train() {
        train = new ArrayList<>();
    }

    public Train(final List<PassengerCarriage> trainNew) {
        train = trainNew;
    }


    @Override
    public void add(final PassengerCarriage passengerCarriageNew) {
        train.add(passengerCarriageNew);
    }

    @Override
    public List<PassengerCarriage> query(final Specification specificationNew) {
        return null;
    }

    @Override
    public int size() {
        return train.size();
    }

    @Override
    public boolean isEmpty() {
        return train.isEmpty();
    }

    @Override
    public PassengerCarriage get(final int index) {
        return train.get(index);
    }

    @Override
    public void set(final PassengerCarriage passengerCarriageNew,
                    final int index) {
        train.set(index, passengerCarriageNew);
    }

    @Override
    public List<PassengerCarriage> getAll() {
        return train;
    }

    @Override
    public boolean equals(final Object oNew) {
        if (this == oNew) {
            return true;
        }
        if (!(oNew instanceof Train)) {
            return false;
        }
        Train train1 = (Train) oNew;
        return Objects.equals(train, train1.train);
    }

    @Override
    public int hashCode() {
        return Objects.hash(train);
    }

    @Override
    public String toString() {
        return "Train{" + "train=" + train + '}';
    }
}
