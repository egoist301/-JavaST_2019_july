package by.training.catalog.dao;

import by.training.catalog.dao.impl.AbstractConnectionManager;

/**
 * Factory interface that is used to creates DAOs.
 */
public interface DaoFactory {
    UserDao createAccountDao(AbstractConnectionManager connectionManager);

    RubikDao createRubikDao(
            AbstractConnectionManager connectionManagerNew);
}
