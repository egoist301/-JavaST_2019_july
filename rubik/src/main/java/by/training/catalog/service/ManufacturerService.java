package by.training.catalog.service;

import by.training.catalog.bean.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    Manufacturer findManufacturerById(long id) throws ServiceException;

    List<Manufacturer> findAll() throws ServiceException;

    List<Manufacturer> findAll(int offset, int limit) throws  ServiceException;

    int findCountManufacturers() throws ServiceException;
}
