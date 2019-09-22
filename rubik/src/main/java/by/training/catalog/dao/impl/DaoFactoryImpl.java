package by.training.catalog.dao.impl;

import by.training.catalog.dao.DaoFactory;
import by.training.catalog.dao.UserDao;

/**
 * Factory for DAO creation.
 */
public class DaoFactoryImpl implements DaoFactory {

    @Override
    public UserDao createAccountDao(AbstractConnectionManager connectionManager) {
        return new UserDaoImpl(connectionManager);
    }

    @Override
    public RubikDaoImpl createRubikDao(AbstractConnectionManager connectionManager) {
        return new RubikDaoImpl(connectionManager);
    }
}
