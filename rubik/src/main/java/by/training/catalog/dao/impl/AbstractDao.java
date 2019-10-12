package by.training.catalog.dao.impl;

import by.training.catalog.bean.Entity;
import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.Dao;

import java.sql.Connection;

/**
 * Abstract class dao.
 *
 * @param <T> some entity.
 */
public abstract class AbstractDao<T extends Entity> implements Dao<T> {
    /**
     * Connection for DB.
     */
    private Connection connection;

    /**
     * Constructor with parameter.
     *
     * @param managerNew connection manager.
     */
    AbstractDao(final AbstractConnectionManager managerNew) {
        connection = managerNew.getConnection();
    }

    /**
     * Getter.
     *
     * @return connection.
     */
    public Connection getConnection() {
        return connection;
    }
}
