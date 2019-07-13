package by.training.transport.model;

import by.training.transport.model.entity.PassengerCarriage;
import by.training.transport.model.specification.Specification;

import java.util.ArrayList;
import java.util.Comparator;
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
     * @param specificationNew
     * @return
     */
    @Override
    public List<PassengerCarriage> query(final Specification specificationNew) {
        return null;
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
     * @param index collection index.
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

    private void quickSort(final Comparator<PassengerCarriage> comparator) {
        List<PassengerCarriage> train = listTrain;
        doSort(train, 0, train.size() - 1, comparator);
    }

    private void doSort(final List<PassengerCarriage> listTrainNew,
                        final int start, final int end,
                        final Comparator<PassengerCarriage> comparator) {
        if (start >= end) {
            return;
        }
        int i = start;
        int j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (comparator.compare(listTrainNew.get(i),
                    listTrainNew.get(cur)) <= 0)) {
                i++;
            }
            while (j > cur && (comparator.compare(listTrainNew.get(cur),
                    listTrainNew.get(j)) <= 0)) {
                j--;
            }
            if (i < j) {
                PassengerCarriage temp = listTrainNew.get(i);
                listTrainNew.set(i, listTrainNew.get(j));
                listTrainNew.set(j, temp);
                if (i == cur) {
                    cur = j;
                } else if (j == cur) {
                    cur = i;
                }
            }
        }
        doSort(listTrainNew, start, cur, comparator);
        doSort(listTrainNew, cur + 1, end, comparator);
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
        return "Train{" + "listTrain=" + listTrain + '}';
    }
}
