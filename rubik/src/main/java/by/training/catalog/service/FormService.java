package by.training.catalog.service;

import by.training.catalog.bean.Form;

import java.util.List;

public interface FormService extends Service {
    Form findFormById(long id) throws ServiceException;

    List<Form> findAll() throws ServiceException;

    List<Form> findAll(int offset, int limit) throws  ServiceException;

    int findCountForms() throws ServiceException;
}
