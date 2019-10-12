package by.training.catalog.dao.impl;

import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.DaoFactory;
import by.training.catalog.dao.RubikDao;
import by.training.catalog.dao.StoreImageDao;
import by.training.catalog.dao.UserDao;

/**
 * Factory for DAO creation.
 */
public class DaoFactoryImpl implements DaoFactory {
    /**
     * Create user dao.
     *
     * @param connectionManager connection manager.
     * @return user dao.
     */
    @Override
    public UserDao createAccountDao(
            final AbstractConnectionManager connectionManager) {
        return new UserDaoImpl(connectionManager);
    }

    /**
     * Create rubik dao.
     *
     * @param connectionManagerNew connection manager.
     * @return rubik dao.
     */
    @Override
    public RubikDao createRubikDao(
            final AbstractConnectionManager connectionManagerNew) {
        return new RubikDaoImpl(connectionManagerNew);
    }

    /**
     * Create store image dao.
     *
     * @param connectionManagerNew connection manager.
     * @return store image dao.
     */
    @Override
    public StoreImageDao createStoreImageDao(
            final AbstractConnectionManager connectionManagerNew) {
        return new StoreImageDaoImpl(connectionManagerNew);
    }
}
