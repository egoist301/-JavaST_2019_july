package by.training.catalog.dao;

import by.training.catalog.dao.impl.AbstractConnectionManager;

/**
 * Factory interface that is used to creates DAOs.
 */
public interface DaoFactory {

    /**
     * Creates AccountDao using specified connection manager.
     * @param connectionManager Connection manager that is used to provide connection to the DAO
     * @return Account dao
     */
    UserDao createAccountDao(AbstractConnectionManager connectionManager);

    /**
     * Creates MessageDao using specified connection manager.
     * @param connectionManager Connection manager that is used to provide connection to the DAO
     * @return Message dao
     */
    RubikDao createMessageDao(AbstractConnectionManager connectionManager);
}
