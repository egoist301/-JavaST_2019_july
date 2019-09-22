package by.training.catalog.service;

import by.training.catalog.bean.Entity;
import by.training.catalog.dao.PersistentException;

import java.util.List;

public interface Service<T extends Entity> {
    T findById(long id) throws PersistentException;

    List<T> findAll() throws PersistentException;

    List<T> findAll(int offset, int limit) throws PersistentException;

    void save(T reader) throws PersistentException;

    int findElementCount() throws PersistentException;
}
