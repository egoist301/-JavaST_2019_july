package by.training.transport.model;

import by.training.transport.model.entity.PassengerCarriage;
import by.training.transport.model.specification.Specification;

import java.util.List;

public interface TrainRepository {
    void add(PassengerCarriage passengerCarriageNew);

    List<PassengerCarriage> query(Specification specificationNew);

    int size();

    boolean isEmpty();

    PassengerCarriage get(int index);

    void set(PassengerCarriage passengerCarriageNew, int index);

    List<PassengerCarriage> getAll();
}
