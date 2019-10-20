package by.training.catalog.dao;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.User;
import by.training.catalog.bean.Role;

import java.util.List;

/**
 * User dao interface.
 */
public interface UserDao extends Dao<User> {
    /**
     * Find account by email.
     *
     * @param email email.
     * @return user.
     * @throws PersistentException dao exception.
     */
    User findAccountByEmail(String email) throws PersistentException;

    /**
     * Find account by login.
     *
     * @param login login or username.
     * @return user.
     * @throws PersistentException dao exception.
     */
    User findAccountByLogin(String login) throws PersistentException;

    /**
     * Find accounts by role in range.
     *
     * @param role   role.
     * @param limit  limit.
     * @param offset offset.
     * @return list of users.
     * @throws PersistentException dao exception.
     */
    List<User> findAccountByRole(Role role, int limit, int offset)
            throws PersistentException;

    /**
     * Find liked cubes by user.
     *
     * @param userNew user.
     * @param limit   limit.
     * @param offset  offset.
     * @return list of cubes.
     * @throws PersistentException dao exception.
     */
    List<RubiksCube> findLikedCubesByUser(User userNew, int limit, int offset)
            throws PersistentException;

    /**
     * Add cube to basket.
     *
     * @param userNew user.
     * @param cubeId cube id.
     * @throws PersistentException dao exception.
     */
    void addCubeToBasket(User userNew, long cubeId)
            throws PersistentException;

    void updateState(User userNew) throws PersistentException;

    /**
     * Remove cube from basket.
     *
     * @param userNew user.
     * @param cubeId cube id.
     * @throws PersistentException dao exception.
     */
    void removeCubeFromBasket(User userNew, long cubeId)
            throws PersistentException;

    RubiksCube findCubeFromBasketById(User userNew,
                                      long cubeId)
            throws PersistentException;
}
