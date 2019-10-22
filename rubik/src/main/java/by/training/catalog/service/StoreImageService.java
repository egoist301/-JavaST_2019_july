package by.training.catalog.service;

import by.training.catalog.bean.RawData;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.StoreImage;

import java.util.List;

/**
 * Store image service interface.
 */
public interface StoreImageService {
    void findImagesByRubik(RubiksCube cubeNew) throws ServiceException;

    List<StoreImage> findAll(int offset, int limit) throws ServiceException;

    StoreImage findEntityById(long id) throws ServiceException;

    void update(StoreImage entityNew) throws ServiceException;

    void create(RubiksCube entityNew, List<RawData> rawDataNew) throws ServiceException;

    int findElementCount() throws ServiceException;
}
