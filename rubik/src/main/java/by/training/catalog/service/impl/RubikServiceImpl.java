package by.training.catalog.service.impl;

import by.training.catalog.bean.RawData;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.StoreImage;
import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.PersistenceException;
import by.training.catalog.dao.RubikDao;
import by.training.catalog.dao.StoreImageDao;
import by.training.catalog.service.AbstractService;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.StoreImageService;
import by.training.catalog.service.factory.RubikFactory;

import java.util.ArrayList;
import java.util.List;

public class RubikServiceImpl extends AbstractService implements RubikService {
    RubikServiceImpl() {
        super();
    }

    @Override
    public List<RubiksCube> findRubiksByUnblocked(final int limit,
                                                  final int offset)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findRubiksByUnblocked(limit, offset);
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public int findCountByUnblocked() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findCountByUnblocked();
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<RubiksCube> findRubiksByManufacturer(final String manufacturer,
                                                     final int limit,
                                                     final int offset)
            throws ServiceException {
        if (manufacturer == null) {
            return new ArrayList<>();
        }
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findRubiksByManufacturer(manufacturer, limit,
                    offset);
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<RubiksCube> findRubiksBySize(final String size,
                                             final int offset, final int limit)
            throws ServiceException {
        if (size == null) {
            return new ArrayList<>();
        }
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findRubiksBySize(size, offset, limit);
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<RubiksCube> findRubiksByModel(final String model,
                                              final int offset, final int limit)
            throws ServiceException {
        if (model == null) {
            return new ArrayList<>();
        }
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findRubikByModel(model, limit, offset);
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<RubiksCube> findRubiksByRangePrice(final String minPrice,
                                                   final String maxPrice,
                                                   final int offset,
                                                   final int limit)
            throws ServiceException {
        if (maxPrice == null || minPrice == null) {
            return new ArrayList<>();
        }
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
        } catch (PersistenceException eNew) {
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
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public int findCountByPrice(final String minPrice, final String maxPrice)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            double max = Double.parseDouble(maxPrice);
            double min = Double.parseDouble(minPrice);
            if (min > max) {
                min += max;
                max = min - max;
                min -= max;
            }
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findCountByPrice(min, max);
        } catch (PersistenceException eNew) {
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
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<RubiksCube> findRubiksByForm(final String form,
                                             final int offset,
                                             final int limit)
            throws ServiceException {
        if (form == null) {
            return new ArrayList<>();
        }
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            return rubikDao.findRubiksByForm(form, offset, limit);
        } catch (PersistenceException eNew) {
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
        } catch (PersistenceException eNew) {
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
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public void update(final long id, final List<String> parameters)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                connectionManager.disableAutoCommit();
                RubikFactory parser = new RubikFactory();
                RubiksCube cube = parser.createCube(parameters);
                cube.setId(id);
                RubikDao rubikDao =
                        getDaoFactory().createRubikDao(connectionManager);
                if (cube.getId() == rubikDao.findEntityById(id).getId()) {
                    rubikDao.update(cube);
                    connectionManager.commit();
                } else {
                    connectionManager.rollback();
                }
            } catch (PersistenceException eNew) {
                connectionManager.rollback();
                throw new ServiceException(eNew);
            }
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public boolean create(final List<String> parameters,
                       final List<RawData> rawDataNew) throws ServiceException {
        boolean flag;
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                connectionManager.disableAutoCommit();
                RubikDao rubikDao =
                        getDaoFactory().createRubikDao(connectionManager);
                RubikFactory parser = new RubikFactory();
                RubiksCube cube = parser.createCube(parameters);
                if (cube != null) {
                    cube.setId(rubikDao.create(cube));
                    addImagesForCube(cube, rawDataNew, connectionManager);
                    connectionManager.commit();
                    flag = true;
                } else {
                    flag = false;
                }
            } catch (PersistenceException eNew) {
                connectionManager.rollback();
                throw new ServiceException(eNew);
            }
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
        return flag;
    }

    private void addImagesForCube(final RubiksCube cubeNew,
                                  final List<RawData> rawData,
                                  final AbstractConnectionManager connectionManager)
            throws PersistenceException {
        List<String> paths = new ArrayList<>();
        ImageService service = new ImageService();
        for (RawData rd : rawData) {
            paths.add(service.save(rd));
        }
        StoreImageDao storeImageDao =
                getDaoFactory().createStoreImageDao(connectionManager);
        for (String path : paths) {
            StoreImage storeImage = new StoreImage(1, cubeNew, path);
            storeImageDao.create(storeImage);
        }
    }

    @Override
    public int findCountBySize(final String size) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                RubikDao rubikDao =
                        getDaoFactory().createRubikDao(connectionManager);
                return rubikDao.findCountBySize(size);
            } catch (PersistenceException eNew) {
                throw new ServiceException(eNew);
            }
        } catch (PersistenceException eNew) {
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
            } catch (PersistenceException eNew) {
                throw new ServiceException(eNew);
            }
        } catch (PersistenceException eNew) {
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
                if (cubeNew != null) {
                    rubikDao.updateState(cubeNew);
                    connectionManager.commit();
                }
            } catch (PersistenceException eNew) {
                connectionManager.rollback();
                throw new ServiceException(eNew);
            }
        } catch (PersistenceException eNew) {
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
        } catch (PersistenceException eNew) {
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
        } catch (PersistenceException eNew) {
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
        } catch (PersistenceException eNew) {
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
        } catch (PersistenceException e) {
            throw new ServiceException(e);
        }
    }
}
