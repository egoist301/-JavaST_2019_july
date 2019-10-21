package by.training.catalog.service;

import by.training.catalog.bean.RawData;
import by.training.catalog.dao.pool.ConnectionPool;

import java.util.ResourceBundle;

/**
 * Initialize connection pool for app. Util class.
 */
public final class ServiceInitializer {
    /**
     * Default private constructor for util class.
     */
    private ServiceInitializer() {
    }

    /**
     * Connect to database.
     *
     * @param bundle resource bundle with parameter for database.
     */
    public static void init(final ResourceBundle bundle, final String path) {
        ConnectionPool.getInstance().init(bundle);
        RawData.setRootPath(path);
    }
}
