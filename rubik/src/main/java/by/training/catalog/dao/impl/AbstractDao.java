package by.training.catalog.dao.impl;

import by.training.catalog.bean.Entity;
import by.training.catalog.dao.Dao;

import java.sql.Connection;

public abstract class AbstractDao<T extends Entity> implements Dao<T> {
    private Connection connection;

    AbstractDao(final Connection connectionNew) {
        connection = connectionNew;
    }

    public Connection getConnection() {
        return connection;
    }
}
