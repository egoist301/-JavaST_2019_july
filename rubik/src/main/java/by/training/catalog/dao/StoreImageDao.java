package by.training.catalog.dao;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.StoreImage;

import java.util.List;

/**
 * Store image dao interface.
 */
public interface StoreImageDao {
    /**
     * Find images by cube.
     *
     * @param cubeNew cube.
     * @return list of paths to image.
     * @throws PersistenceException if SQL exception is throw.
     */
    List<String> findImagesByRubik(RubiksCube cubeNew)
            throws PersistenceException;

    /**
     * Create store image.
     *
     * @param entityNew object.
     * @return id of store image.
     * @throws PersistenceException if SQL exception is throw.
     */
    int create(StoreImage entityNew) throws PersistenceException;
}
