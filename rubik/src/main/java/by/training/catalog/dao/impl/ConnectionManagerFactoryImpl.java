package by.training.catalog.dao.impl;

import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.ConnectionManagerFactory;
import by.training.catalog.dao.PersistentException;

public class ConnectionManagerFactoryImpl implements ConnectionManagerFactory {
    @Override
    public AbstractConnectionManager createConnectionManager()
            throws PersistentException {
        return new ConnectionManager();
    }
}
