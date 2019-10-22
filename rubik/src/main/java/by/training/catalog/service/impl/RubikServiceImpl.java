package by.training.catalog.service.impl;

import by.training.catalog.bean.RawData;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.ConnectionManagerFactory;
import by.training.catalog.dao.DaoFactory;
import by.training.catalog.dao.PersistentException;
import by.training.catalog.dao.RubikDao;
import by.training.catalog.service.AbstractService;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.StoreImageService;
import by.training.catalog.service.parser.RubikParser;

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
    public List<RubiksCube> findRubiksByManufacturer(final String manufacturer,
                                                     final int limit,
                                                     final int offset)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findRubiksByManufacturer(manufacturer, limit,
                    offset);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
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
    public List<RubiksCube> findRubiksByModel(final String model,
                                              final int offset, final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findRubikByModel(model, limit, offset);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<RubiksCube> findRubiksByRangePrice(final String minPrice,
                                                   final String maxPrice,
                                                   final int offset,
                                                   final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            double min = Double.parseDouble(minPrice);
            double max = Double.parseDouble(maxPrice);
            if (min > max) {
                min += max;
                max = min - max;
                min -= max;
            }
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao
                    .findRubiksByRangePrice(min, max, offset, limit);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public int findCountByForm(final String form) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findCountByForm(form);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public int findCountByPrice(final String min, final String max)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findCountByPrice(Double.parseDouble(min),
                    Double.parseDouble(max));
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public int findCountByManufacturer(final String manufacturer)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findCountByManufacturer(manufacturer);
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
    public void update(final RubiksCube entityNew,
                       final List<RawData> rawDataNew) throws ServiceException {
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
    public void create(final List<String> parameters,
                       final List<RawData> rawDataNew) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                connectionManager.disableAutoCommit();
                RubikDao rubikDao =
                        getDaoFactory().createRubikDao(connectionManager);
                RubikParser parser = new RubikParser();
                RubiksCube cube = parser.getCube(parameters);
                StoreImageService storeImageService =
                        new StoreImageServiceImpl();
                cube.setId(rubikDao.create(cube));
                connectionManager.commit();
                storeImageService.create(cube, rawDataNew);
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
    public int findCountByModel(final String model) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                RubikDao rubikDao =
                        getDaoFactory().createRubikDao(connectionManager);
                return rubikDao.findCountByModel(model);
            } catch (PersistentException eNew) {
                throw new ServiceException(eNew);
            }
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public void updateState(final long id) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                connectionManager.disableAutoCommit();
                RubikDao rubikDao =
                        getDaoFactory().createRubikDao(connectionManager);
                RubiksCube cubeNew = findById(id);
                rubikDao.updateState(cubeNew);
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
