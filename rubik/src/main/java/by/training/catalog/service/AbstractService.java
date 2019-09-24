package by.training.catalog.service;

import by.training.catalog.dao.DaoFactory;
import by.training.catalog.dao.impl.DaoFactoryImpl;

public class AbstractService {
    private DaoFactory daoFactory;

    public AbstractService() {
        daoFactory = new DaoFactoryImpl();
    }

    public AbstractService(final DaoFactory daoFactoryNew) {
        daoFactory = daoFactoryNew;
    }

    public DaoFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(final DaoFactory daoFactoryNew) {
        daoFactory = daoFactoryNew;
    }
}
