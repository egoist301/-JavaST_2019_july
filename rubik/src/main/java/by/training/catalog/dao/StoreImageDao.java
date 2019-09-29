package by.training.catalog.dao;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.StoreImage;

import java.util.List;

public interface StoreImageDao extends Dao<StoreImage> {
    List<String> findImagesByRubik(RubiksCube cubeNew)
            throws PersistentException;
}
