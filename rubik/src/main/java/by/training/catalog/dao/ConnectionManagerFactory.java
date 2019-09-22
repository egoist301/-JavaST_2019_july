package by.training.catalog.dao;

import by.training.catalog.dao.impl.AbstractConnectionManager;

/**
 * Factory which creates {@link AbstractConnectionManager} subclass instances.
 */
public interface ConnectionManagerFactory {

    /**
     * Creates {@link AbstractConnectionManager} subclass instance.
     *
     * @return {@link AbstractConnectionManager} subclass instance
     * @throws PersistentException If SQLException is thrown while creating ConnectionManager
     */
    AbstractConnectionManager createConnectionManager()
            throws PersistentException;
}
