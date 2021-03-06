package by.training.catalog.service.impl;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.PersistenceException;
import by.training.catalog.dao.StoreImageDao;
import by.training.catalog.service.AbstractService;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.StoreImageService;

public class StoreImageServiceImpl extends AbstractService implements
        StoreImageService {

    @Override
    public void assignRubikImagesPaths(final RubiksCube cubeNew)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            if (cubeNew != null) {
                StoreImageDao storeImageDao =
                        getDaoFactory().createStoreImageDao(connectionManager);
                cubeNew.setPaths(storeImageDao.findImagesByRubik(cubeNew));
            }
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }
}
