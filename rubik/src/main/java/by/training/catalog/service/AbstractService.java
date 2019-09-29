package by.training.catalog.service;

import by.training.catalog.dao.ConnectionManagerFactory;
import by.training.catalog.dao.DaoFactory;
import by.training.catalog.dao.impl.ConnectionManagerFactoryImpl;
import by.training.catalog.dao.impl.DaoFactoryImpl;

public class AbstractService {
    private DaoFactory daoFactory;
    private ConnectionManagerFactory connectionManagerFactory;

    public AbstractService() {
        daoFactory = new DaoFactoryImpl();
        connectionManagerFactory = new ConnectionManagerFactoryImpl();
    }

    public AbstractService(final DaoFactory daoFactoryNew,
                           final ConnectionManagerFactory
                                   connectionManagerFactoryNew) {
        daoFactory = daoFactoryNew;
        connectionManagerFactory = connectionManagerFactoryNew;
    }

    public DaoFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(final DaoFactory daoFactoryNew) {
        daoFactory = daoFactoryNew;
    }

    public ConnectionManagerFactory getConnectionManagerFactory() {
        return connectionManagerFactory;
    }

    public void setConnectionManagerFactory(
            final ConnectionManagerFactory connectionManagerFactoryNew) {
        connectionManagerFactory = connectionManagerFactoryNew;
    }
}
