package by.training.catalog.dao.impl;

import by.training.catalog.bean.Role;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.User;
import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.PersistenceException;
import by.training.catalog.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * User dao implementation. Connect to database.
 */
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    /**
     * Find user by... SQL query.
     */
    private static final String FIND_USER_BY = "SELECT `id`, "
            + "`username`, `password`, `email`, `role`, `phone`, `blocked` "
            + "FROM `users` WHERE ";
    /**
     * Find user by email. SQL query.
     */
    private static final String FIND_USER_BY_EMAIL =
            FIND_USER_BY + "`email` = ?";
    /**
     * Find count of users. SQL query.
     */
    private static final String FIND_USER_COUNT =
            "SELECT COUNT(`id`) FROM `users`";
    /**
     * Find user by id. SQL query.
     */
    private static final String FIND_USER_BY_ID =
            FIND_USER_BY + "`id` = ?";
    /**
     * Find users by username. SQL query.
     */
    private static final String FIND_USERS_BY_USERNAME =
            FIND_USER_BY + "`username` LIKE ? LIMIT ? OFFSET ?";
    /**
     * Update user by id. SQL query.
     */
    private static final String UPDATE_USER_BY_ID =
            "UPDATE `users` SET `password` = ? WHERE `id` = ?";
    /**
     * Find all users page. SQL query.
     */
    private static final String FIND_ALL_USERS_PAGE = "SELECT `id`, "
            + "`username`, `password`, `email`, `role`, `phone`, `blocked` "
            + "FROM `users` ORDER BY id LIMIT ? OFFSET ?";
    /**
     * Insert user. SQL query.
     */
    private static final String INSERT_USER =
            "INSERT INTO `users` (`username`, "
                    + "`password`, `email`, `role`, `phone`, `blocked`) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
    /**
     * Find users by role. SQL query.
     */
    private static final String FIND_USERS_BY_ROLE = FIND_USER_BY
            + "`role` = ? LIMIT ? OFFSET ?";
    /**
     * Find user by username. SQL query.
     */
    private static final String FIND_USER_BY_USERNAME =
            FIND_USER_BY + " `username` = ?";
    /**
     * Find all rubiks page. SQL query.
     */
    private static final String FIND_ALL_RUBIKS_PAGE =
            "SELECT `cube_id` FROM bookmarks WHERE `user_id` = ? "
                    + "LIMIT ? OFFSET ?";
    /**
     * Add rubik to bookmarks. SQL query.
     */
    private static final String ADD_RUBIK =
            "INSERT INTO bookmarks(`cube_id`, `user_id`) VALUES "
                    + "(?, ?)";
    private static final String FIND_COUNT_RUBIKS = "SELECT COUNT(cube_id) "
            + "FROM bookmarks WHERE user_id = ?";
    /**
     * Delete rubik(dislike). SQL query.
     */
    private static final String DELETE_RUBIK =
            "DELETE FROM bookmarks WHERE `cube_id` = ? AND "
                    + "`user_id` = ?";
    /**
     * Find rubik from bookmarks. SQL query.
     */
    private static final String FIND_RUBIK_FROM_BOOKMARKS = "SELECT `cube_id` "
            + "FROM bookmarks WHERE `user_id` = ? AND `cube_id` = ?";
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Update state of user. Ban. SQL query.
     */
    private static final String UPDATE_STATE = "UPDATE `users` SET `blocked` = "
            + "true WHERE `id` = ?";

    /**
     * Constructor.
     *
     * @param managerNew connection manager.
     */
    public UserDaoImpl(final AbstractConnectionManager managerNew) {
        super(managerNew);
    }

    @Override
    public int findCountOfRubiks(final User userNew)
            throws PersistenceException {
        try (PreparedStatement statement =
                     getConnection().prepareStatement(FIND_COUNT_RUBIKS)) {
            statement.setLong(1, userNew.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException eNew) {
            throw new PersistenceException(eNew);
        }
        return 0;
    }

    /**
     * Find user by email.
     *
     * @param email email.
     * @return user.
     * @throws PersistenceException sql exception.
     */
    @Override
    public User findUserByEmail(final String email)
            throws PersistenceException {
        User user = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = createUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(
                    "SQLException while finding by email", e);
        }
        return user;
    }

    /**
     * Find count of users.
     *
     * @return count of users.
     * @throws PersistenceException sql exception.
     */
    @Override
    public int findElementCount() throws PersistenceException {
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_USER_COUNT)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(
                    "SQLException while finding count users", e);
        }
        return 0;
    }

    /**
     * Find user by username.
     *
     * @param username username.
     * @return user.
     * @throws PersistenceException sql exception.
     */
    @Override
    public User findUserByUsername(final String username)
            throws PersistenceException {
        User user = null;
        try (PreparedStatement statement =
                     getConnection().prepareStatement(
                             FIND_USER_BY_USERNAME)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = createUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(
                    "SQLException while finding by phone", e);
        }
        return user;
    }

    /**
     * Find users by role.
     *
     * @param role   role.
     * @param limit  limit.
     * @param offset offset.
     * @return list of users.
     * @throws PersistenceException sql exception.
     */
    @Override
    public List<User> findUsersByRole(final Role role, final int limit,
                                      final int offset)
            throws PersistenceException {
        List<User> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_USERS_BY_ROLE)) {
            int temp = 0;
            preparedStatement.setInt(++temp, role.ordinal());
            preparedStatement.setInt(++temp, limit);
            preparedStatement.setInt(++temp, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(createUserFromResultSet(resultSet));
                }
            }
            return users;
        } catch (SQLException newE) {
            throw new PersistenceException(newE.getMessage(), newE);
        }
    }

    /**
     * Find liked cubes by user.
     *
     * @param userNew user.
     * @param limit   limit.
     * @param offset  offset.
     * @return list of liked cubes.
     * @throws PersistenceException sql exception.
     */
    @Override
    public List<RubiksCube> findLikedCubesByUser(final User userNew,
                                                 final int limit,
                                                 final int offset)
            throws PersistenceException {
        List<RubiksCube> rubiksCubes = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_ALL_RUBIKS_PAGE)) {
            int temp = 0;
            preparedStatement.setLong(++temp, userNew.getId());
            preparedStatement.setInt(++temp, limit);
            preparedStatement.setInt(++temp, offset);
            LOGGER.debug(preparedStatement);
            LOGGER.debug("Id {}, limit {}, offset {}", userNew.getId(), limit,
                    offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    rubiksCubes.add(new RubiksCube(resultSet.getLong(
                            "cube_id")));
                }
            }
            LOGGER.debug(rubiksCubes);
            return rubiksCubes;
        } catch (SQLException newE) {
            throw new PersistenceException(newE.getMessage(), newE);
        }
    }

    /**
     * Find all users in range.
     *
     * @param offset offset.
     * @param limit  limit.
     * @return list of users.
     * @throws PersistenceException sql exception.
     */
    @Override
    public List<User> findAll(final int offset, final int limit)
            throws PersistenceException {
        List<User> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_ALL_USERS_PAGE)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(createUserFromResultSet(resultSet));
                }
            }
            return users;
        } catch (SQLException newE) {
            throw new PersistenceException(newE.getMessage(), newE);
        }
    }

    /**
     * Find user by id.
     *
     * @param id id of object.
     * @return user.
     * @throws PersistenceException sql exception.
     */
    @Override
    public User findEntityById(final long id) throws PersistenceException {
        User user = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_USER_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = createUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException("SQLException while finding by id",
                    e);
        }
        return user;
    }

    /**
     * Update user.
     *
     * @param entityNew object.
     * @throws PersistenceException sql exception.
     */
    @Override
    public void update(final User entityNew) throws PersistenceException {
        try (PreparedStatement statement = getConnection()
                .prepareStatement(UPDATE_USER_BY_ID)) {
            statement.setString(1, entityNew.getPassword());
            statement.setLong(2, entityNew.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("SQLException while updating", e);
        }
    }

    /**
     * Add user in DB.
     *
     * @param user object.
     * @return id of user.
     * @throws PersistenceException sql exception.
     */
    @Override
    public int create(final User user) throws PersistenceException {
        if (user == null) {
            return 0;
        }
        try (PreparedStatement statement = getConnection()
                .prepareStatement(INSERT_USER, RETURN_GENERATED_KEYS)) {
            int temp = 0;
            statement.setString(++temp, user.getUsername());
            statement.setString(++temp, user.getPassword());
            statement.setString(++temp, user.getEmail());
            statement.setInt(++temp, user.getRole().ordinal());
            statement.setInt(++temp, user.getPhone());
            statement.setBoolean(++temp, user.isBlocked());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(
                    "SQLException while inserting user", e);
        }
        return 0;
    }

    /**
     * Find users by username.
     *
     * @param username username.
     * @param limit    limit.
     * @param offset   offset.
     * @return list of users.
     * @throws PersistenceException sql exception.
     */
    @Override
    public List<User> findUsersByUsername(final String username,
                                          final int limit,
                                          final int offset)
            throws PersistenceException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement =
                     getConnection()
                             .prepareStatement(FIND_USERS_BY_USERNAME)) {
            int temp = 0;
            statement.setString(++temp, username + '%');
            statement.setInt(++temp, limit);
            statement.setInt(++temp, offset);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(createUserFromResultSet(resultSet));
                }
            }
        } catch (SQLException eNew) {
            throw new PersistenceException(eNew);
        }
        return users;
    }

    /**
     * Add cube to bookmarks. Like.
     *
     * @param userNew user.
     * @param cubeId  cube id.
     * @throws PersistenceException sql exception.
     */
    @Override
    public void addCubeToBookmarks(final User userNew, final long cubeId)
            throws PersistenceException {
        try (PreparedStatement statement =
                     getConnection().prepareStatement(ADD_RUBIK,
                             RETURN_GENERATED_KEYS)) {
            statement.setLong(1, cubeId);
            statement.setLong(2, userNew.getId());
            statement.executeUpdate();
        } catch (SQLException eNew) {
            throw new PersistenceException(eNew);
        }
    }

    /**
     * Update state of user. Ban.
     *
     * @param userNew user.
     * @throws PersistenceException sql exception.
     */
    @Override
    public void updateState(final User userNew) throws PersistenceException {
        try (PreparedStatement statement =
                     getConnection().prepareStatement(UPDATE_STATE)) {
            statement.setLong(1, userNew.getId());
            statement.executeUpdate();
        } catch (SQLException eNew) {
            throw new PersistenceException(eNew);
        }
    }

    /**
     * Remove cube from bookmarks. Dislike.
     *
     * @param userNew user.
     * @param cubeId  cube id.
     * @throws PersistenceException sql exception.
     */
    @Override
    public void removeCubeFromBookmarks(final User userNew,
                                        final long cubeId)
            throws PersistenceException {
        try (PreparedStatement statement =
                     getConnection().prepareStatement(DELETE_RUBIK)) {
            statement.setLong(1, cubeId);
            statement.setLong(2, userNew.getId());
            statement.executeUpdate();
        } catch (SQLException eNew) {
            throw new PersistenceException(eNew);
        }
    }

    /**
     * Find cube from bookmarks by id.
     *
     * @param userNew user.
     * @param cubeId  cube id.
     * @return cube.
     * @throws PersistenceException dao exception.
     */
    @Override
    public RubiksCube findCubeFromBookmarksById(final User userNew,
                                                final long cubeId)
            throws PersistenceException {
        if (userNew == null) {
            return null;
        }
        RubiksCube cube = null;
        try (PreparedStatement statement =
                     getConnection()
                             .prepareStatement(FIND_RUBIK_FROM_BOOKMARKS)) {
            statement.setLong(1, userNew.getId());
            statement.setLong(2, cubeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cube = new RubiksCube(resultSet.getLong("cube_id"));
                }
            }
        } catch (SQLException eNew) {
            throw new PersistenceException(eNew);
        }
        return cube;
    }

    /**
     * Create user from result set.
     *
     * @param resultSet result set.
     * @return user.
     * @throws SQLException sql exception.
     */
    private User createUserFromResultSet(final ResultSet resultSet)
            throws SQLException {
        long accountId = resultSet.getLong("id");
        String username = resultSet.getString("username");
        String passwordHash = resultSet.getString("password");
        String email = resultSet.getString("email");
        Role role = Role.values()[resultSet.getInt("role")];
        int phone = resultSet.getInt("phone");
        boolean blocked = resultSet.getBoolean("blocked");
        return new User(accountId, username, passwordHash, email, role,
                phone, blocked);
    }
}
