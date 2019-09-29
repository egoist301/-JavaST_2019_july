package by.training.catalog.dao.impl;

import by.training.catalog.dao.*;

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
    public StoreImageDao createStoreImageDao(
            final AbstractConnectionManager connectionManagerNew) {
        return new StoreImageDaoImpl(connectionManagerNew);
    }
}
