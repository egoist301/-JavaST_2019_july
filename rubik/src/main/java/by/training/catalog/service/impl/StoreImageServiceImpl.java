package by.training.catalog.service.impl;

import by.training.catalog.bean.RawData;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.StoreImage;
import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.PersistentException;
import by.training.catalog.dao.StoreImageDao;
import by.training.catalog.service.AbstractService;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.StoreImageService;

import java.util.ArrayList;
import java.util.List;

public class StoreImageServiceImpl extends AbstractService implements
        StoreImageService {

    @Override
    public void findImagesByRubik(final RubiksCube cubeNew)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            StoreImageDao storeImageDao =
                    getDaoFactory().createStoreImageDao(connectionManager);
            cubeNew.setPaths(storeImageDao.findImagesByRubik(cubeNew));
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<StoreImage> findAll(final int offset, final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            StoreImageDao storeImageDao =
                    getDaoFactory().createStoreImageDao(connectionManager);
            return storeImageDao.findAll(offset, limit);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public StoreImage findEntityById(final long id) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            StoreImageDao storeImageDao =
                    getDaoFactory().createStoreImageDao(connectionManager);
            return storeImageDao.findEntityById(id);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public void update(final StoreImage entityNew) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            StoreImageDao storeImageDao =
                    getDaoFactory().createStoreImageDao(connectionManager);
            storeImageDao.update(entityNew);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public void create(final RubiksCube cubeNew, final List<RawData> rawData)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                connectionManager.disableAutoCommit();
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
    public int findElementCount() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            StoreImageDao storeImageDao =
                    getDaoFactory().createStoreImageDao(connectionManager);
            return storeImageDao.findElementCount();
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }
}
