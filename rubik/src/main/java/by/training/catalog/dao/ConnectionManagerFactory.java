package by.training.catalog.dao;

/**
 * Connection manager factory interface.
 */
public interface ConnectionManagerFactory {
    /**
     * Create connection manager.
     *
     * @return connection manager.
     * @throws PersistenceException dao exception.
     */
    AbstractConnectionManager createConnectionManager()
            throws PersistenceException;
}
