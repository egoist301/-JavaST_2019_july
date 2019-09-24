package by.training.catalog.dao.impl;

import by.training.catalog.dao.DaoFactory;
import by.training.catalog.dao.FormDao;
import by.training.catalog.dao.ManufacturerDao;
import by.training.catalog.dao.PlasticColorDao;
import by.training.catalog.dao.RubikDao;
import by.training.catalog.dao.UserDao;

/**
 * Factory for DAO creation.
 */
public class DaoFactoryImpl implements DaoFactory {
    @Override
    public UserDao createAccountDao(
            final AbstractConnectionManager connectionManager) {
        return new UserDaoImpl(connectionManager);
    }

    @Override
    public RubikDao createRubikDao(
            final AbstractConnectionManager connectionManagerNew) {
        return new RubikDaoImpl(connectionManagerNew);
    }

    @Override
    public FormDao createFormDao(
            final AbstractConnectionManager connectionManagerNew) {
        return new FormDaoImpl(connectionManagerNew);
    }

    @Override
    public PlasticColorDao createPlasticColorDao(
            final AbstractConnectionManager connectionManagerNew) {
        return new PlasticColorDaoImpl(connectionManagerNew);
    }

    @Override
    public ManufacturerDao createManufacturerDao(
            final AbstractConnectionManager connectionManagerNew) {
        return new ManufacturerDaoImpl(connectionManagerNew);
    }
}
