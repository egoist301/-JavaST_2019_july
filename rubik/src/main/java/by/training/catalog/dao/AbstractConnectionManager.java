package by.training.catalog.dao;

import java.sql.Connection;

/**
 * Abstract class that subclasses are intended to provide
 * connection manipulation methods.
 * Used by service layer to provide connection to DAOs.
 */
public interface AbstractConnectionManager extends AutoCloseable {

    /**
     * Disables auto commit.
     *
     * @throws PersistenceException if SQL exception is throw.
     */
    void disableAutoCommit() throws PersistenceException;

    /**
     * Enables auto commit.
     *
     * @throws PersistenceException if SQL exception is throw.
     */
    void enableAutoCommit() throws PersistenceException;

    /**
     * Delegates commit call to the connection.
     *
     * @throws PersistenceException if SQL exception is throw.
     */
    void commit() throws PersistenceException;

    /**
     * Delegates rollback call to the connection.
     *
     * @throws PersistenceException if SQL exception is throw.
     */
    void rollback() throws PersistenceException;

    /**
     * Close.
     *
     * @throws PersistenceException if SQL exception is throw.
     */
    void close() throws PersistenceException;

    /**
     * Getter.
     *
     * @return connection.
     */
    Connection getConnection();
}
