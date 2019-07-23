package by.training.transport.service;

import by.training.transport.dao.factory.CarriageFactory;
import by.training.transport.dao.factory.CarriageProvider;
import by.training.transport.dao.factory.exception.CarriageValidationException;
import by.training.transport.dao.parser.DataParser;
import by.training.transport.dao.reader.DataReader;
import by.training.transport.dao.repository.Train;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class TrainFactory {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Singleton.
     */
    private static final TrainFactory TRAIN_FACTORY = new TrainFactory();

    private TrainFactory() {
    }

    /**
     * @return train factory.
     */
    public static TrainFactory getTrainFactory() {
        return TRAIN_FACTORY;
    }

    /**
     * @param filepath filepath.
     */
    public void createTrain(final String filepath) {
        DataReader dataReader = new DataReader();
        DataParser dataParser = new DataParser();
        CarriageProvider carriageProvider = new CarriageProvider();
        Train train = Train.getTrain();
        for (String[] strings : dataParser
                .getLines(dataReader.readAll(filepath))) {
            try {
                CarriageFactory carriageFactory =
                        carriageProvider.selectCarriageFactory(strings[0]);
                train.add(carriageFactory.createCarriage(strings));
            } catch (CarriageValidationException ex) {
                LOGGER.warn(ex);
            }
        }
    }
}
