package by.training.catalog.dao;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.StoreImage;

import java.util.List;

/**
 * Store image dao interface.
 */
public interface StoreImageDao extends Dao<StoreImage> {
    /**
     * Find images by cube.
     *
     * @param cubeNew cube.
     * @return list of paths to image.
     * @throws PersistentException if SQL exception is throw.
     */
    List<String> findImagesByRubik(RubiksCube cubeNew)
            throws PersistentException;
}
