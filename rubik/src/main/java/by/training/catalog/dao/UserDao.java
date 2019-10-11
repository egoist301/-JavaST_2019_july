package by.training.catalog.dao;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.User;
import by.training.catalog.bean.Role;

import java.util.List;

public interface UserDao extends Dao<User> {
    User findAccountByUsername(String username) throws PersistentException;

    User findAccountByEmail(String email) throws PersistentException;

    User findAccountByPhone(int phone) throws PersistentException;

    User findAccountByLogin(String login, String password)
            throws PersistentException;

    List<User> findAccountByRole(Role role, int limit, int offset)
            throws PersistentException;

    List<RubiksCube> findLikedCubesByUser(User userNew, int limit, int offset)
            throws PersistentException;

    void addCubeToBasket(User userNew, RubiksCube cubeNew)
            throws PersistentException;

    void removeCubeFromBasket(User userNew, RubiksCube cubeNew)
            throws PersistentException;
}
