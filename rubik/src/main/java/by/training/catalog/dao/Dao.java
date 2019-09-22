package by.training.catalog.dao;

import by.training.catalog.bean.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * DAO interface.
 */
public interface Dao<T extends Entity> {
    Logger LOGGER = LogManager.getLogger();

    List<T> findAll() throws PersistentException;

    List<T> findAll(int offset, int limit) throws PersistentException;

    T findEntityById(long id) throws PersistentException;

    void update(T entityNew) throws PersistentException;

    boolean create(T entityNew) throws PersistentException;

    int findElementCount() throws PersistentException;
}
