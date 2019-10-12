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
     * @throws PersistentException if SQL exception is throw.
     */
    void disableAutoCommit() throws PersistentException;

    /**
     * Enables auto commit.
     *
     * @throws PersistentException if SQL exception is throw.
     */
    void enableAutoCommit() throws PersistentException;

    /**
     * Delegates commit call to the connection.
     *
     * @throws PersistentException if SQL exception is throw.
     */
    void commit() throws PersistentException;

    /**
     * Delegates rollback call to the connection.
     *
     * @throws PersistentException if SQL exception is throw.
     */
    void rollback() throws PersistentException;

    /**
     * Close.
     *
     * @throws PersistentException if SQL exception is throw.
     */
    void close() throws PersistentException;

    /**
     * Getter.
     *
     * @return connection.
     */
    Connection getConnection();
}
