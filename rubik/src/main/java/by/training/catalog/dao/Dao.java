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
     * @throws PersistentException dao exception.
     */
    List<T> findAll(int offset, int limit) throws PersistentException;

    /**
     * Find object by id.
     *
     * @param id id of object.
     * @return object.
     * @throws PersistentException dao exception.
     */
    T findEntityById(long id) throws PersistentException;

    /**
     * Update info about object.
     *
     * @param entityNew object.
     * @throws PersistentException dao exception.
     */
    void update(T entityNew) throws PersistentException;

    /**
     * Create object.
     *
     * @param entityNew object.
     * @return id of object.
     * @throws PersistentException dao exception.
     */
    int create(T entityNew) throws PersistentException;

    /**
     * Find count of element.
     *
     * @return count of element.
     * @throws PersistentException dao exception.
     */
    int findElementCount() throws PersistentException;
}
