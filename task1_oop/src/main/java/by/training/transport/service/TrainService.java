package by.training.transport.service;

import by.training.transport.bean.entity.PassengerCarriage;
import by.training.transport.dao.repository.Train;

public final class TrainService {
    private TrainService() {
    }

    /**
     * @param trainNew train.
     * @return number of passengers in train.
     */
    public static int countingTheNumberOfPassengers(final Train trainNew) {
        int result = 0;
        for (PassengerCarriage passengerCarriage : trainNew.getAll()) {
            result += passengerCarriage.getNumberOfPassengers();
        }
        return result;
    }

    /**
     * @param trainNew train.
     * @return number of luggage in train.
     */
    public static int countingTheNumberOfLuggage(final Train trainNew) {
        int result = 0;
        for (PassengerCarriage passengerCarriage : trainNew.getAll()) {
            result += passengerCarriage.getNumberOfLuggage();
        }
        return result;
    }
}
