package by.training.catalog.service;

import by.training.catalog.bean.Role;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.User;

import java.util.List;

public interface UserService extends Service {
    void update(User entityNew) throws ServiceException;

    boolean create(String username, String email, String phone,
                   String password) throws ServiceException;

    User findUserByEmail(String email) throws ServiceException;

    List<User> findUsersByUsername(String username,
                                   int limit, int offset)
            throws ServiceException;

    User findUserByUsername(String username) throws ServiceException;

    User authorize(String login, String password)
            throws ServiceException;

    List<User> findUsersByRole(Role role, int limit, int offset)
            throws ServiceException;

    User findById(long id) throws ServiceException;

    List<User> findAll(int offset, int limit) throws ServiceException;

    void updateState(long id) throws ServiceException;

    void findLikedCubes(User userNew, int limit, int offset)
            throws ServiceException;

    boolean addCubeToBasket(User userNew, long cubeId) throws ServiceException;

    RubiksCube findCubeFromBookmarks(User userNew, long cubeId)
            throws ServiceException;

    void removeFromBookmarks(User userNew, long id) throws ServiceException;

    int findElementCount() throws ServiceException;
}
