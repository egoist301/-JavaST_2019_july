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

    List<T> findAll() throws PersistenceException;

    T findEntityById(long id) throws PersistenceException;

    T update(T entityNew) throws PersistenceException;

    boolean create(T entityNew) throws PersistenceException;

    boolean delete(long id) throws PersistenceException;
}
