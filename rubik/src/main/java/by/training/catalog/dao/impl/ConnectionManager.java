package by.training.catalog.dao.impl;

import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.PersistentException;
import by.training.catalog.dao.pool.ConnectionPool;
import by.training.catalog.dao.pool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class that instances are used to provide connection to DAOs.
 */
public class ConnectionManager implements AbstractConnectionManager {
    /**
     * Connection that is being provided.
     */
    private Connection connection;

    /**
     * Creates ConnectionManager that takes connection from the connection pool.
     *
     * @throws PersistentException if ConnectionPoolException is thrown
     */
    public ConnectionManager() throws PersistentException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            disableAutoCommit();
        } catch (ConnectionPoolException e) {
            throw new PersistentException(
                    "unable to get connection from the pool", e);
        }
    }

    /**
     * Disables auto commit.
     *
     * @throws PersistentException if SQLException is thrown
     */
    @Override
    public void disableAutoCommit() throws PersistentException {
        try {
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            throw new PersistentException("could not set auto commit to false",
                    e);
        }
    }

    /**
     * Enables auto commit.
     *
     * @throws PersistentException If SQLException is thrown
     */
    @Override
    public void enableAutoCommit() throws PersistentException {
        try {
            if (!connection.getAutoCommit()) {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new PersistentException("could not set auto commit to true",
                    e);
        }
    }

    /**
     * Delegates commit call to the connection.
     *
     * @throws PersistentException If SQLException is thrown
     */
    @Override
    public void commit() throws PersistentException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new PersistentException("could not commit", e);
        }
    }

    /**
     * Delegates rollback call to the connection.
     *
     * @throws PersistentException If SQLException is thrown
     */
    @Override
    public void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new PersistentException("could not rollback", e);
        }
    }

    /**
     * Delegates close call to the connection.
     *
     * @throws PersistentException If SQLException is thrown
     */
    @Override
    public void close() throws PersistentException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new PersistentException(
                    "unable to return connection to the pool", e);
        }
    }

    /**
     * Returns {@link ConnectionManager#connection}.
     * Used to provide connection to DAO.
     *
     * @return {@link ConnectionManager#connection}
     */
    @Override
    public Connection getConnection() {
        return connection;
    }
}
