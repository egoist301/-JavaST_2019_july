package by.training.catalog.dao;

import by.training.catalog.bean.User;
import by.training.catalog.bean.Role;

import java.util.List;

public interface UserDao extends Dao<User> {
    User findAccountByUsername(String username) throws PersistentException;

    User findAccountByEmail(String email) throws PersistentException;

    User findAccountByPhone(int phone) throws PersistentException;

    int findAccountCount() throws PersistentException;

    User findAccountByLoginAndPassword(String login, String password)
            throws PersistentException;

    List<User> findAccountByRole(Role role, int limit, int offset)
            throws PersistentException;
}
