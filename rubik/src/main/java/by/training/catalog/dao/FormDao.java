package by.training.catalog.dao;

import by.training.catalog.bean.Form;
import java.util.List;

public interface FormDao {
    Form findFormById(long id) throws PersistentException;

    List<Form> findAll() throws PersistentException;

    List<Form> findAll(int offset, int limit) throws  PersistentException;

    int findCountForms() throws PersistentException;
}
