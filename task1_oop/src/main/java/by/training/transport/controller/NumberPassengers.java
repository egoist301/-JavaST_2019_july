package by.training.transport.controller;

import by.training.transport.dao.repository.Train;
import by.training.transport.service.TrainService;

public class NumberPassengers {
    /**
     * @return number of passengers.
     */
    public int passesTheNumberOfPassengers() {
        return TrainService.countingTheNumberOfPassengers(Train.getTrain());
    }
}
