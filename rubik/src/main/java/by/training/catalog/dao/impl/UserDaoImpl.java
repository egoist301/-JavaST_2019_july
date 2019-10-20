package by.training.catalog.dao.impl;

import by.training.catalog.bean.Role;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.User;
import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.PersistentException;
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

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final String FIND_USER_BY = "SELECT `id`, "
            + "`username`, `password`, `email`, `role`, `phone`, `blocked` "
            + "FROM `users` WHERE ";
    private static final String FIND_USER_BY_EMAIL =
            FIND_USER_BY + "`email` = ?";
    private static final String FIND_USER_COUNT =
            "SELECT COUNT(`id`) FROM `users`";
    private static final String FIND_USER_BY_ID =
            FIND_USER_BY + "`id` = ?";
    private static final String FIND_USERS_BY_USERNAME =
            FIND_USER_BY + "`username` LIKE ? LIMIT ? OFFSET ?";
    private static final String UPDATE_USER_BY_ID =
            "UPDATE `users` SET `username` = ?, `password` = ?, `email` ="
                    + " ?, `role` = ?, `phone` = ?, `blocked` = ? WHERE `id` ="
                    + " ?";
    private static final String FIND_ALL_USERS_PAGE = "SELECT `id`, "
            + "`username`, `password`, `email`, `role`, `phone`, `blocked` "
            + "FROM `users` ORDER BY id LIMIT ? OFFSET ?";
    private static final String INSERT_USER =
            "INSERT INTO `users` (`username`, "
                    + "`password`, `email`, `role`, `phone`, `blocked`) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_USERS_BY_ROLE = FIND_USER_BY
            + "`role` = ? LIMIT ? OFFSET ?";
    private static final String FIND_USER_BY_USERNAME =
            FIND_USER_BY + " `username` = ?";
    private static final String FIND_ALL_RUBIKS_PAGE =
            "SELECT `cube_id` FROM bookmarks WHERE `user_id` = ? "
                    + "LIMIT ? OFFSET ?";
    private static final String ADD_RUBIK =
            "INSERT INTO bookmarks(`cube_id`, `user_id`) VALUES "
                    + "(?, ?)";
    private static final String DELETE_RUBIK =
            "DELETE FROM bookmarks WHERE `cube_id` = ? AND "
                    + "`user_id` = ?";
    private static final String FIND_RUBIK_FROM_BASKET = "SELECT `cube_id` "
            + "FROM bookmarks WHERE `user_id` = ? AND `cube_id` = ?";
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String UPDATE_STATE = "UPDATE `users` SET `blocked` = "
            + "true WHERE `id` = ?";

    public UserDaoImpl(final AbstractConnectionManager managerNew) {
        super(managerNew);
    }

    @Override
    public User findUserByEmail(final String email)
            throws PersistentException {
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
            throw new PersistentException(
                    "SQLException while finding by email", e);
        }
        return user;
    }

    @Override
    public int findElementCount() throws PersistentException {
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_USER_COUNT)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding count users", e);
        }
        return 0;
    }

    @Override
    public User findUserByUsername(final String username)
            throws PersistentException {
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
            throw new PersistentException(
                    "SQLException while finding by phone", e);
        }
        return user;
    }

    @Override
    public List<User> findUsersByRole(final Role role, final int limit,
                                      final int offset)
            throws PersistentException {
        List<User> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_USERS_BY_ROLE)) {
            preparedStatement.setInt(1, role.ordinal() + 1);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(createUserFromResultSet(resultSet));
                }
            }
            return users;
        } catch (SQLException newE) {
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<RubiksCube> findLikedCubesByUser(final User userNew,
                                                 final int limit,
                                                 final int offset)
            throws PersistentException {
        List<RubiksCube> rubiksCubes = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_ALL_RUBIKS_PAGE)) {
            preparedStatement.setLong(1, userNew.getId());
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
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
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<User> findAll(final int offset, final int limit)
            throws PersistentException {
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
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public User findEntityById(final long id) throws PersistentException {
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
            throw new PersistentException("SQLException while finding by id",
                    e);
        }
        return user;
    }

    @Override
    public void update(final User entityNew) throws PersistentException {
        try (PreparedStatement statement = getConnection()
                .prepareStatement(UPDATE_USER_BY_ID)) {
            execute(statement, entityNew);
            statement.setLong(7, entityNew.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException("SQLException while updating", e);
        }
    }

    @Override
    public int create(final User entityNew) throws PersistentException {
        if (entityNew == null) {
            return 0;
        }
        try (PreparedStatement statement = getConnection()
                .prepareStatement(INSERT_USER, RETURN_GENERATED_KEYS)) {
            execute(statement, entityNew);
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while inserting user", e);
        }
        return 0;
    }

    @Override
    public List<User> findUsersByUsername(final String username, final int limit,
                                          final int offset)
            throws PersistentException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement =
                     getConnection()
                             .prepareStatement(FIND_USERS_BY_USERNAME)) {
            statement.setString(1, username + '%');
            statement.setInt(2, limit);
            statement.setInt(3, offset);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(createUserFromResultSet(resultSet));
                }
            }
        } catch (SQLException eNew) {
            throw new PersistentException(eNew);
        }
        return users;
    }

    @Override
    public void addCubeToBookmarks(final User userNew, final long cubeId)
            throws PersistentException {
        try (PreparedStatement statement =
                     getConnection().prepareStatement(ADD_RUBIK,
                             RETURN_GENERATED_KEYS)) {
            statement.setLong(1, cubeId);
            statement.setLong(2, userNew.getId());
            statement.executeUpdate();
        } catch (SQLException eNew) {
            throw new PersistentException(eNew);
        }
    }

    @Override
    public void updateState(final User userNew) throws PersistentException {
        try (PreparedStatement statement =
                     getConnection().prepareStatement(UPDATE_STATE)) {
            statement.setLong(1, userNew.getId());
            statement.executeUpdate();
        } catch (SQLException eNew) {
            throw new PersistentException(eNew);
        }
    }

    @Override
    public void removeCubeFromBookmarks(final User userNew,
                                        final long cubeId)
            throws PersistentException {
        try (PreparedStatement statement =
                     getConnection().prepareStatement(DELETE_RUBIK)) {
            statement.setLong(1, cubeId);
            statement.setLong(2, userNew.getId());
            statement.executeUpdate();
        } catch (SQLException eNew) {
            throw new PersistentException(eNew);
        }
    }

    @Override
    public RubiksCube findCubeFromBookmarksById(final User userNew,
                                                final long cubeId)
            throws PersistentException {
        try (PreparedStatement statement =
                     getConnection().prepareStatement(FIND_RUBIK_FROM_BASKET)) {
            statement.setLong(1, userNew.getId());
            statement.setLong(2, cubeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new RubiksCube(resultSet.getLong("cube_id"));
                }
            }
        } catch (SQLException eNew) {
            throw new PersistentException(eNew);
        }
        return null;
    }

    private void execute(final PreparedStatement statement,
                         final User entityNew)
            throws SQLException {
        statement.setString(1, entityNew.getUsername());
        statement.setString(2, entityNew.getPassword());
        statement.setString(3, entityNew.getEmail());
        statement.setInt(4, entityNew.getRole().ordinal());
        statement.setInt(5, entityNew.getPhone());
        statement.setBoolean(6, entityNew.isBlocked());
    }

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
