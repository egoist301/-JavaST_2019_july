package by.training.catalog.dao;

import by.training.catalog.bean.PlasticColor;

import java.util.List;

public interface PlasticColorDao {
    PlasticColor findPlasticColorById(long id) throws PersistentException;

    List<PlasticColor> findAll() throws PersistentException;

    List<PlasticColor> findAll(int offset, int limit)
            throws PersistentException;

    int findCountColors() throws PersistentException;
}
