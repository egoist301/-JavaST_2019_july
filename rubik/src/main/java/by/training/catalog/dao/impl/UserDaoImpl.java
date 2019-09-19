package by.training.catalog.dao.impl;

import by.training.catalog.bean.User;
import by.training.catalog.bean.Role;
import by.training.catalog.dao.PersistentException;
import by.training.catalog.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final String FIND_ACCOUNT_BY = "SELECT `id`, "
            + "`username`, `password`, `email`, `role`, `phone` FROM `users`"
            + " WHERE ";
    private static final String FIND_ACCOUNT_BY_USERNAME = FIND_ACCOUNT_BY +
            "`username` = ?";
    private static final String FIND_ACCOUNT_BY_EMAIL =
            FIND_ACCOUNT_BY + "`email` = ?";
    private static final String FIND_ACCOUNT_BY_PHONE =
            FIND_ACCOUNT_BY + "`phone` = ?";
    private static final String FIND_ACCOUNT_COUNT =
            "SELECT COUNT(`id`) FROM `users`";
    private static final String FIND_ALL_ACCOUNTS = "SELECT `id`, `username`, "
            + "`password`, `email`, `role`, `phone` FROM `users` ORDER BY "
            + "`username`";
    private static final String FIND_ACCOUNT_BY_ID =
            FIND_ACCOUNT_BY + "`id` = ?";
    private static String DELETE_ACCOUNT_BY_ID =
            "DELETE FROM `users` WHERE `id` = ?";
    private static final String UPDATE_ACCOUNT_BY_ID =
            "UPDATE `users` SET `username` = ?, `password` = ?, `email` ="
                    + " ?, `role` = ?, `phone` = ? WHERE `id` = ?";
    private static final String FIND_ALL_ACCOUNTS_PAGE = "SELECT `id`, "
            + "`username`, `password`, `email`, `role`, `phone` FROM `users` "
            + "ORDER BY id LIMIT ? OFFSET ?";
    private static final String INSERT_ACCOUNT =
            "INSERT INTO `users` (`username`, "
                    + "`password`, `email`, `role`, `phone`) "
                    + "VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_ACCOUNTS_BY_ROLE = FIND_ACCOUNT_BY +
            "`role` = ? LIMIT ? OFFSET ?";
    private static final String FIND_ACCOUNT_BY_LOGIN_AND_PASSWORD =
            FIND_ACCOUNT_BY + "`username` = ? AND `password` = ?";

    public UserDaoImpl(final Connection connectionNew) {
        super(connectionNew);
    }

    @Override
    public User findAccountByUsername(final String username)
            throws PersistentException {
        if (username == null) {
            return null;
        }
        User user = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_ACCOUNT_BY_USERNAME)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = createAccountFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new PersistentException("SQLException while finding by "
                    + "username", e);
        }
        return user;
    }

    @Override
    public User findAccountByEmail(final String email)
            throws PersistentException {
        if (email == null) {
            return null;
        }
        User user = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_ACCOUNT_BY_EMAIL)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = createAccountFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding by email", e);
        }
        return user;
    }

    @Override
    public User findAccountByPhone(final int phone)
            throws PersistentException {
        User user = null;
        try (PreparedStatement statement =
                     getConnection().prepareStatement(FIND_ACCOUNT_BY_PHONE)) {
            statement.setInt(1, phone);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = createAccountFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding by phone", e);
        }
        return user;
    }

    @Override
    public int findAccountCount() throws PersistentException {
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_ACCOUNT_COUNT)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding page accounts sorted by rating",
                    e);
        }
        return 0;
    }

    @Override
    public User findAccountByLoginAndPassword(final String login,
                                              final String password)
            throws PersistentException {
        User user = null;
        try (PreparedStatement statement =
                     getConnection().prepareStatement(
                             FIND_ACCOUNT_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = createAccountFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding by phone", e);
        }
        return user;
    }

    @Override
    public List<User> findAccountByRole(final Role role, final int limit,
                                        final int offset)
            throws PersistentException {
        List<User> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_ACCOUNTS_BY_ROLE)) {
            preparedStatement.setInt(1, role.ordinal() + 1);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(createAccountFromResultSet(resultSet));
                }
            }
            return users;
        } catch (SQLException newE) {
            LOGGER.warn(newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<User> findAll() throws PersistentException {
        List<User> list = new LinkedList<>();
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_ALL_ACCOUNTS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                list.add(createAccountFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding all accounts", e);
        }
        return list;
    }

    @Override
    public List<User> findAll(final int offset, final int limit)
            throws PersistentException {
        List<User> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_ALL_ACCOUNTS_PAGE)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(createAccountFromResultSet(resultSet));
                }
            }
            return users;
        } catch (SQLException newE) {
            LOGGER.warn(newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public User findEntityById(final long id) throws PersistentException {
        User user = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_ACCOUNT_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = createAccountFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new PersistentException("SQLException while finding by id",
                    e);
        }
        return user;
    }

    @Override
    public boolean delete(final long id) throws PersistentException {
        try (PreparedStatement statement = getConnection()
                .prepareStatement(DELETE_ACCOUNT_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public User update(final User entityNew) throws PersistentException {
        try (PreparedStatement statement = getConnection()
                .prepareStatement(UPDATE_ACCOUNT_BY_ID)) {
            statement.setString(1, entityNew.getUsername());
            statement.setString(2, entityNew.getPassword());
            statement.setString(3, entityNew.getEmail());
            statement.setInt(4, entityNew.getRole().getIdentity());
            statement.setInt(5, entityNew.getPhone());
            statement.setLong(6, entityNew.getId());
            return entityNew;
        } catch (SQLException e) {
            throw new PersistentException("SQLException while updating", e);
        }
    }

    @Override
    public boolean create(final User entityNew) throws PersistentException {
        if (entityNew == null) {
            return false;
        }
        try (PreparedStatement statement = getConnection()
                .prepareStatement(INSERT_ACCOUNT)) {
            statement.setString(1, entityNew.getUsername());
            statement.setString(2, entityNew.getPassword());
            statement.setString(3, entityNew.getEmail());
            statement.setInt(4, entityNew.getRole().getIdentity());
            statement.setInt(5, entityNew.getPhone());
            int result = statement.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while inserting account", e);
        }
    }

    private User createAccountFromResultSet(ResultSet resultSet)
            throws SQLException {
        long accountId = resultSet.getLong(1);
        String username = resultSet.getString(2);
        String passwordHash = resultSet.getString(3);
        String email = resultSet.getString(4);
        Role role = Role.values()[resultSet.getInt(5)];
        int phone = resultSet.getInt(6);
        boolean blocked = resultSet.getBoolean(7);
        return new User(accountId, username, passwordHash, role, email,
                phone, blocked);
    }
}
