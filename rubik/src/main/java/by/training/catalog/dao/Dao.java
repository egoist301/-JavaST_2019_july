package by.training.catalog.dao;

import by.training.catalog.bean.Entity;

import java.util.List;


/**
 * Dao interface.
 *
 * @param <T> some entity.
 */
public interface Dao<T extends Entity> {
    /**
     * Find all objects in range.
     *
     * @param offset offset.
     * @param limit  limit.
     * @return list of objects.
     * @throws PersistenceException dao exception.
     */
    List<T> findAll(int offset, int limit) throws PersistenceException;

    /**
     * Find object by id.
     *
     * @param id id of object.
     * @return object.
     * @throws PersistenceException dao exception.
     */
    T findEntityById(long id) throws PersistenceException;

    /**
     * Update info about object.
     *
     * @param entityNew object.
     * @throws PersistenceException dao exception.
     */
    void update(T entityNew) throws PersistenceException;

    /**
     * Create object.
     *
     * @param entityNew object.
     * @return id of object.
     * @throws PersistenceException dao exception.
     */
    int create(T entityNew) throws PersistenceException;

    /**
     * Find count of element.
     *
     * @return count of element.
     * @throws PersistenceException dao exception.
     */
    int findElementCount() throws PersistenceException;
}
