package by.training.transport.controller;

import by.training.transport.dao.repository.Train;
import by.training.transport.service.TrainService;

public class NumberLuggage {
    /**
     * @return number of luggage.
     */
    public int execute() {
        return TrainService.countingTheNumberOfLuggage(Train.getTrain());
    }
}
