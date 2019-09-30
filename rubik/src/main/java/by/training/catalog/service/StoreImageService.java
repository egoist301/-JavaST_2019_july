package by.training.catalog.service;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.StoreImage;

import java.util.List;

public interface StoreImageService extends Service {
    List<String> findImagesByRubik(RubiksCube cubeNew) throws ServiceException;

    List<StoreImage> findAll() throws ServiceException;

    List<StoreImage> findAll(int offset, int limit) throws ServiceException;

    StoreImage findEntityById(long id) throws ServiceException;

    void update(StoreImage entityNew) throws ServiceException;

    void create(StoreImage entityNew) throws ServiceException;

    int findElementCount() throws ServiceException;
}
