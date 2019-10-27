package by.training.catalog.dao;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.User;
import by.training.catalog.bean.Role;

import java.util.List;

/**
 * User dao interface.
 */
public interface UserDao extends Dao<User> {
    int findCountOfRubiks(User userNew)
            throws PersistenceException;

    /**
     * Find user by email.
     *
     * @param email email.
     * @return user.
     * @throws PersistenceException dao exception.
     */
    User findUserByEmail(String email) throws PersistenceException;

    /**
     * Find user by username.
     *
     * @param username username.
     * @return user.
     * @throws PersistenceException dao exception.
     */
    User findUserByUsername(String username) throws PersistenceException;

    /**
     * Find users by role in range.
     *
     * @param role   role.
     * @param limit  limit.
     * @param offset offset.
     * @return list of users.
     * @throws PersistenceException dao exception.
     */
    List<User> findUsersByRole(Role role, int limit, int offset)
            throws PersistenceException;

    /**
     * Find liked cubes by user.
     *
     * @param userNew user.
     * @param limit   limit.
     * @param offset  offset.
     * @return list of cubes.
     * @throws PersistenceException dao exception.
     */
    List<RubiksCube> findLikedCubesByUser(User userNew, int limit, int offset)
            throws PersistenceException;

    /**
     * Find users by username.
     *
     * @param username username.
     * @param limit    limit.
     * @param offset   offset.
     * @return list of users.
     * @throws PersistenceException dao exception.
     */
    List<User> findUsersByUsername(String username, int limit,
                                   int offset)
            throws PersistenceException;

    /**
     * Add cube to bookmarks.
     *
     * @param userNew user.
     * @param cubeId  cube id.
     * @throws PersistenceException dao exception.
     */
    void addCubeToBookmarks(User userNew, long cubeId)
            throws PersistenceException;

    /**
     * Update state of user. Ban.
     *
     * @param userNew user.
     * @throws PersistenceException dao exception.
     */
    void updateState(User userNew) throws PersistenceException;

    /**
     * Remove cube from bookmarks.
     *
     * @param userNew user.
     * @param cubeId  cube id.
     * @throws PersistenceException dao exception.
     */
    void removeCubeFromBookmarks(User userNew, long cubeId)
            throws PersistenceException;

    /**
     * Find cube from bookmarks by cube id.
     *
     * @param userNew user.
     * @param cubeId  cube id.
     * @return cube.
     * @throws PersistenceException dao exception.
     */
    RubiksCube findCubeFromBookmarksById(User userNew,
                                         long cubeId)
            throws PersistenceException;
}
