package by.training.catalog.service.impl;

import by.training.catalog.bean.PlasticColor;
import by.training.catalog.dao.DaoFactory;
import by.training.catalog.dao.PersistentException;
import by.training.catalog.dao.PlasticColorDao;
import by.training.catalog.dao.impl.AbstractConnectionManager;
import by.training.catalog.dao.impl.ConnectionManager;
import by.training.catalog.service.AbstractService;
import by.training.catalog.service.PlasticColorService;
import by.training.catalog.service.ServiceException;

import java.util.List;

public class PlasticColorServiceImpl extends AbstractService implements
        PlasticColorService {
    public PlasticColorServiceImpl() {
        super();
    }

    public PlasticColorServiceImpl(
            final DaoFactory daoFactoryNew) {
        super(daoFactoryNew);
    }

    @Override
    public PlasticColor findPlasticColorById(final long id)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            PlasticColorDao plasticColorDao =
                    getDaoFactory().createPlasticColorDao(connectionManager);
            return plasticColorDao.findPlasticColorById(id);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<PlasticColor> findAll() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            PlasticColorDao plasticColorDao =
                    getDaoFactory().createPlasticColorDao(connectionManager);
            return plasticColorDao.findAll();
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<PlasticColor> findAll(final int offset, final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            PlasticColorDao plasticColorDao =
                    getDaoFactory().createPlasticColorDao(connectionManager);
            return plasticColorDao.findAll(offset, limit);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public int findCountColors() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            PlasticColorDao plasticColorDao =
                    getDaoFactory().createPlasticColorDao(connectionManager);
            return plasticColorDao.findCountColors();
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }
}
