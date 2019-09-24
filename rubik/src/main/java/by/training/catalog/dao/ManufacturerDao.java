package by.training.catalog.dao;

import by.training.catalog.bean.Manufacturer;

import java.util.List;

public interface ManufacturerDao {
    Manufacturer findManufacturerById(long id) throws PersistentException;

    List<Manufacturer> findAll() throws PersistentException;

    List<Manufacturer> findAll(int offset, int limit) throws  PersistentException;

    int findCountManufacturers() throws PersistentException;
}
