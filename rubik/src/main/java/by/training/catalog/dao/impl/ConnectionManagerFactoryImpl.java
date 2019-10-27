package by.training.catalog.dao.impl;

import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.ConnectionManagerFactory;
import by.training.catalog.dao.PersistenceException;

/**
 * Connection manager factory.
 */
public class ConnectionManagerFactoryImpl implements ConnectionManagerFactory {
    /**
     * Create connection manager.
     *
     * @return connection manager.
     * @throws PersistenceException dao exception.
     */
    @Override
    public AbstractConnectionManager createConnectionManager()
            throws PersistenceException {
        return new ConnectionManager();
    }
}
