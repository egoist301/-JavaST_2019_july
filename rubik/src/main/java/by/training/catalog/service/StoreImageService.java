package by.training.catalog.service;

import by.training.catalog.bean.RubiksCube;

/**
 * Store image service interface.
 */
public interface StoreImageService {
    void assignRubikImagesPaths(RubiksCube cubeNew) throws ServiceException;
}
