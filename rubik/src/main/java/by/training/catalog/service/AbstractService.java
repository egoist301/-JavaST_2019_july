package by.training.catalog.service;

import by.training.catalog.dao.ConnectionManagerFactory;
import by.training.catalog.dao.DaoFactory;
import by.training.catalog.dao.impl.ConnectionManagerFactoryImpl;
import by.training.catalog.dao.impl.DaoFactoryImpl;

/**
 * Abstract class for service.
 */
public abstract class AbstractService {
    /**
     * Factory of dao.
     */
    private DaoFactory daoFactory;
    /**
     * Connection manager factory.
     */
    private ConnectionManagerFactory connectionManagerFactory;

    /**
     * Default constructor.
     */
    public AbstractService() {
        daoFactory = new DaoFactoryImpl();
        connectionManagerFactory = new ConnectionManagerFactoryImpl();
    }

    /**
     * Getter for dao factory.
     *
     * @return factory of dao.
     */
    protected DaoFactory getDaoFactory() {
        return daoFactory;
    }

    /**
     * Getter for connection manager.
     *
     * @return connection manager factory.
     */
    protected ConnectionManagerFactory getConnectionManagerFactory() {
        return connectionManagerFactory;
    }
}
