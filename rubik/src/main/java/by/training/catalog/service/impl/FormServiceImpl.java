package by.training.catalog.service.impl;

import by.training.catalog.bean.Form;
import by.training.catalog.dao.DaoFactory;
import by.training.catalog.dao.FormDao;
import by.training.catalog.dao.PersistentException;
import by.training.catalog.dao.impl.AbstractConnectionManager;
import by.training.catalog.dao.impl.ConnectionManager;
import by.training.catalog.service.AbstractService;
import by.training.catalog.service.FormService;
import by.training.catalog.service.ServiceException;

import java.util.List;

public class FormServiceImpl extends AbstractService implements FormService {
    public FormServiceImpl() {
        super();
    }

    public FormServiceImpl(final DaoFactory daoFactoryNew) {
        super(daoFactoryNew);
    }

    @Override
    public Form findFormById(final long id) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            FormDao formDao = getDaoFactory().createFormDao(connectionManager);
            return formDao.findFormById(id);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<Form> findAll() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            FormDao formDao = getDaoFactory().createFormDao(connectionManager);
            return formDao.findAll();
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<Form> findAll(final int offset, final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            FormDao formDao = getDaoFactory().createFormDao(connectionManager);
            return formDao.findAll(offset, limit);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public int findCountForms() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            FormDao formDao = getDaoFactory().createFormDao(connectionManager);
            return formDao.findCountForms();
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }
}
