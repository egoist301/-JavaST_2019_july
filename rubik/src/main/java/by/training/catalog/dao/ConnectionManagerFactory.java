package by.training.catalog.dao;

public interface ConnectionManagerFactory {
    AbstractConnectionManager createConnectionManager()
            throws PersistentException;
}
