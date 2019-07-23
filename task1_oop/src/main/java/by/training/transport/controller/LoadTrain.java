package by.training.transport.controller;

import by.training.transport.service.TrainFactory;

public class LoadTrain {
    /**
     * @param filepath filepath.
     */
    public void load(final String filepath) {
        TrainFactory trainFactory = TrainFactory.getTrainFactory();
        trainFactory.createTrain(filepath);
    }
}
