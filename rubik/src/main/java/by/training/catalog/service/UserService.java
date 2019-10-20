package by.training.catalog.service;

import by.training.catalog.bean.Role;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.User;
import by.training.catalog.dao.PersistentException;

import java.util.List;

/**
 * User service. Service for add and manipulate database users.
 */
public interface UserService {
    /**
     * Update user info.
     *
     * @param entityNew user.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    void update(User entityNew) throws ServiceException;

    /**
     * Create user.
     *
     * @param username username.
     * @param email    email.
     * @param phone    phone.
     * @param password password.
     * @return true or false.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    boolean create(String username, String email, String phone,
                   String password) throws ServiceException;

    /**
     * Find user by email.
     *
     * @param email email.
     * @return user.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    User findUserByEmail(String email) throws ServiceException;

    /**
     * Find users by username.
     *
     * @param username username.
     * @param limit    limit.
     * @param offset   offset.
     * @return list of users.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    List<User> findUsersByUsername(String username,
                                   int limit, int offset)
            throws ServiceException;

    /**
     * Find user by username.
     *
     * @param username username.
     * @return user.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    User findUserByUsername(String username) throws ServiceException;

    /**
     * Authorize.
     *
     * @param username username.
     * @param password password.
     * @return user.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    User authorize(String username, String password)
            throws ServiceException;

    /**
     * Find users by role.
     *
     * @param role   role.
     * @param limit  limit.
     * @param offset offset.
     * @return list of users.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    List<User> findUsersByRole(Role role, int limit, int offset)
            throws ServiceException;

    /**
     * Find user by id.
     *
     * @param id id of user.
     * @return user.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    User findById(long id) throws ServiceException;

    /**
     * Find all users in range.
     *
     * @param offset offset.
     * @param limit  limit.
     * @return list of users.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    List<User> findAll(int offset, int limit) throws ServiceException;

    /**
     * Update state of user.
     *
     * @param id id of user.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    void updateState(long id) throws ServiceException;

    /**
     * Find liked cubes of user.
     *
     * @param userNew user.
     * @param limit   limit.
     * @param offset  offset.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    void findLikedCubes(User userNew, int limit, int offset)
            throws ServiceException;

    /**
     * Add cube to bookmarks.
     *
     * @param userNew user.
     * @param cubeId  id of cube.
     * @return true or false.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    boolean addCubeToBookmarks(User userNew, long cubeId)
            throws ServiceException;

    /**
     * Find cube from bookmarks.
     *
     * @param userNew user.
     * @param cubeId  id of cube.
     * @return cube.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    RubiksCube findCubeFromBookmarks(User userNew, long cubeId)
            throws ServiceException;

    /**
     * Remove cube from bookmarks.
     *
     * @param userNew user.
     * @param id      id of cube.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    void removeFromBookmarks(User userNew, long id) throws ServiceException;

    /**
     * Find count of users.
     *
     * @return count of users.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    int findElementCount() throws ServiceException;
}
