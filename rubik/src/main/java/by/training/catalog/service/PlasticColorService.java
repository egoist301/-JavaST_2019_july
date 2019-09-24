package by.training.catalog.service;

import by.training.catalog.bean.PlasticColor;

import java.util.List;

public interface PlasticColorService extends Service {
    PlasticColor findPlasticColorById(long id) throws ServiceException;

    List<PlasticColor> findAll() throws ServiceException;

    List<PlasticColor> findAll(int offset, int limit)
            throws ServiceException;

    int findCountColors() throws ServiceException;
}
