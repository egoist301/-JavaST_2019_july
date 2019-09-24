package by.training.catalog.service.impl;

import by.training.catalog.bean.Manufacturer;
import by.training.catalog.dao.DaoFactory;
import by.training.catalog.dao.ManufacturerDao;
import by.training.catalog.dao.PersistentException;
import by.training.catalog.dao.impl.AbstractConnectionManager;
import by.training.catalog.dao.impl.ConnectionManager;
import by.training.catalog.service.AbstractService;
import by.training.catalog.service.ManufacturerService;
import by.training.catalog.service.ServiceException;

import java.util.List;

public class ManufacturerServiceImpl extends AbstractService implements
        ManufacturerService {
    public ManufacturerServiceImpl() {
        super();
    }

    public ManufacturerServiceImpl(
            final DaoFactory daoFactoryNew) {
        super(daoFactoryNew);
    }

    @Override
    public Manufacturer findManufacturerById(final long id)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            ManufacturerDao manufacturerDao =
                    getDaoFactory().createManufacturerDao(connectionManager);
            return manufacturerDao.findManufacturerById(id);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<Manufacturer> findAll() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            ManufacturerDao manufacturerDao =
                    getDaoFactory().createManufacturerDao(connectionManager);
            return manufacturerDao.findAll();
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<Manufacturer> findAll(final int offset, final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            ManufacturerDao manufacturerDao =
                    getDaoFactory().createManufacturerDao(connectionManager);
            return manufacturerDao.findAll(offset, limit);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public int findCountManufacturers() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            ManufacturerDao manufacturerDao =
                    getDaoFactory().createManufacturerDao(connectionManager);
            return manufacturerDao.findCountManufacturers();
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }
}
