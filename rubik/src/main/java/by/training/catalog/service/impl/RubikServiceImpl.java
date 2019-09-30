package by.training.catalog.service.impl;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.dao.ConnectionManagerFactory;
import by.training.catalog.dao.DaoFactory;
import by.training.catalog.dao.PersistentException;
import by.training.catalog.dao.RubikDao;
import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.service.AbstractService;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;

import java.util.List;

public class RubikServiceImpl extends AbstractService implements RubikService {
    public RubikServiceImpl() {
        super();
    }

    public RubikServiceImpl(final DaoFactory daoFactoryNew,
                            final ConnectionManagerFactory
                                    connectionManagerFactoryNew) {
        super(daoFactoryNew, connectionManagerFactoryNew);
    }

    @Override
    public List<RubiksCube> findRubiksBySize(final String size,
                                             final int offset, final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findRubiksBySize(size, offset, limit);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public RubiksCube findRubikByModel(final String model)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findRubikByModel(model);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<RubiksCube> findRubiksByRangePrice(final int minPrice,
                                                   final int maxPrice,
                                                   final int offset,
                                                   final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao
                    .findRubiksByRangePrice(minPrice, maxPrice, offset, limit);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<RubiksCube> findRubiksByForm(final String form,
                                             final int offset,
                                             final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findRubiksByForm(form, offset, limit);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public RubiksCube findById(final long id) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findEntityById(id);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<RubiksCube> findAll() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findAll();
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<RubiksCube> findAll(final int offset, final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findAll(offset, limit);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public void update(final RubiksCube entityNew) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                RubikDao rubikDao =
                        getDaoFactory().createRubikDao(connectionManager);
                rubikDao.update(entityNew);
                connectionManager.commit();
            } catch (PersistentException eNew) {
                connectionManager.rollback();
                throw new ServiceException(eNew);
            }
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public void create(final RubiksCube entityNew) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                RubikDao rubikDao =
                        getDaoFactory().createRubikDao(connectionManager);
                entityNew.setId(rubikDao.create(entityNew));
                connectionManager.commit();
            } catch (PersistentException eNew) {
                connectionManager.rollback();
                throw new ServiceException(eNew);
            }
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<String> readAllManufacturer() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.readAllManufacturer();
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<String> readAllForm() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.readAllForm();
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<String> readAllPlasticColor() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.readAllPlasticColor();
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public int findElementCount() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findElementCount();
        } catch (PersistentException e) {
            throw new ServiceException(e);
        }
    }
}
