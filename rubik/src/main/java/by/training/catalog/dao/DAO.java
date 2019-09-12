package by.training.catalog.dao;

import by.training.catalog.bean.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * DAO interface.
 */
public interface DAO<T extends Entity> {
    Logger LOGGER = LogManager.getLogger();

    List<T> findAll() throws PersistenceException;

    T findEntityById(int id) throws PersistenceException;

    T update(T entityNew) throws PersistenceException;

    boolean create(T entityNew) throws PersistenceException;

    boolean delete(T entityNew) throws PersistenceException;

    default void close(Statement statementNew) throws PersistenceException {
        try {
            statementNew.close();
        } catch (SQLException eNew) {
            throw new PersistenceException();
        }
    }
}
