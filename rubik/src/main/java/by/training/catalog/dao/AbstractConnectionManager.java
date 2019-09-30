package by.training.catalog.dao;

import java.sql.Connection;

/**
 * Abstract class that subclasses are intended to provide
 * connection manipulation methods.
 * Used by service layer to provide connection to DAOs.
 */
public interface AbstractConnectionManager extends AutoCloseable {

    void disableAutoCommit() throws PersistentException;
    void enableAutoCommit() throws PersistentException;
    void commit() throws PersistentException;
    void rollback() throws PersistentException;
    void close() throws PersistentException;
    Connection getConnection();
}
