package by.training.catalog.service;

import by.training.catalog.bean.Role;
import by.training.catalog.bean.User;

import java.util.List;

public interface UserService extends Service {
    void update(final User entityNew) throws ServiceException;

    void create(final User entityNew) throws ServiceException;

    User findAccountByUsername(String username) throws ServiceException;

    User findAccountByEmail(String email) throws ServiceException;

    User findAccountByPhone(int phone) throws ServiceException;

    User findAccountByLoginAndPassword(String login, String password)
            throws ServiceException;

    List<User> findAccountByRole(Role role, int limit, int offset)
            throws ServiceException;

    User findById(long id) throws ServiceException;

    List<User> findAll() throws ServiceException;

    List<User> findAll(int offset, int limit) throws ServiceException;

    int findElementCount() throws ServiceException;
}
