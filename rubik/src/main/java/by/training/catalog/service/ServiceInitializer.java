package by.training.catalog.service;

import by.training.catalog.dao.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceInitializer {
    private static Logger logger =
            LogManager.getLogger(ServiceInitializer.class);

    public static void init() throws ServiceException {

        ConnectionPool.getInstance().init();

    }
}
