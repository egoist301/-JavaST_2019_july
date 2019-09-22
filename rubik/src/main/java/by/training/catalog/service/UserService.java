package by.training.catalog.service;

import by.training.catalog.bean.Role;
import by.training.catalog.bean.User;
import by.training.catalog.dao.PersistentException;

import java.util.List;

public interface UserService extends Service<User> {
    User findAccountByUsername(String username) throws PersistentException;

    User findAccountByEmail(String email) throws PersistentException;

    User findAccountByPhone(int phone) throws PersistentException;

    User findAccountByLoginAndPassword(String login, String password)
            throws PersistentException;

    List<User> findAccountByRole(Role role, int limit, int offset)
            throws PersistentException;
}
