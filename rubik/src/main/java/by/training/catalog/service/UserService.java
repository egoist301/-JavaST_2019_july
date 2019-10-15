package by.training.catalog.service;

import by.training.catalog.bean.Role;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.User;

import java.util.List;

public interface UserService extends Service {
    void update(User entityNew) throws ServiceException;

    void create(String username, String email, String phone,
                String password) throws ServiceException;

    User findAccountByEmail(String email) throws ServiceException;

    User findAccountByPhone(int phone) throws ServiceException;

    User findAccountByLogin(String login) throws ServiceException;

    User findAccountByLoginAndPassword(String login, String password)
            throws ServiceException;

    List<User> findAccountByRole(Role role, int limit, int offset)
            throws ServiceException;

    User findById(long id) throws ServiceException;

    List<User> findAll() throws ServiceException;

    List<User> findAll(int offset, int limit) throws ServiceException;

    void findLikedCubes(User userNew, int limit, int offset)
            throws ServiceException;

    void addCubeToBasket(User userNew, RubiksCube cubeNew)
            throws ServiceException;

    RubiksCube findCubeFromBasket(User userNew,
                                  RubiksCube rubiksCubeNew)
            throws ServiceException;

    void removeFromBasket(User userNew, RubiksCube cubeNew)
            throws ServiceException;

    int findElementCount() throws ServiceException;
}
