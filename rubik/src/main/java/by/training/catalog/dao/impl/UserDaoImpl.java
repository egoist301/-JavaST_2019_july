package by.training.catalog.dao.impl;

import by.training.catalog.bean.Account;
import by.training.catalog.bean.Role;
import by.training.catalog.dao.PersistenceException;
import by.training.catalog.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl extends AbstractDao<Account> implements UserDao {
    public UserDaoImpl(final Connection connectionNew) {
        super(connectionNew);
    }

    @Override
    public Account findAccountByUsername(final String username)
            throws PersistenceException {
        if (username == null) {
            return null;
        }
        final String FIND_ACCOUNT_BY_USERNAME = "SELECT `id`, `username`, "
                + "`password`, `email`, `role`, `phone` FROM `users` WHERE "
                + "`username` = ?";
        Account account = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_ACCOUNT_BY_USERNAME)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    account = createAccountFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException("SQLException while finding by "
                    + "username", e);
        }
        return account;
    }

    @Override
    public Account findAccountByEmail(final String email)
            throws PersistenceException {
        if (email == null) {
            return null;
        }
        final String FIND_ACCOUNT_BY_EMAIL = "SELECT `id`, `username`, "
                + "`password`, `email`, `role`, `phone` FROM `users` WHERE "
                + "`email` = ?";
        Account account = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_ACCOUNT_BY_EMAIL)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    account = createAccountFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(
                    "SQLException while finding by email", e);
        }
        return account;
    }

    @Override
    public Account findAccountByPhone(final int phone)
            throws PersistenceException {
        final String FIND_ACCOUNT_BY_PHONE = "SELECT `id`, `username`, "
                + "`password`, `email`, `role`, `phone` FROM `users` WHERE "
                + "`phone` = ?";
        Account account = null;
        try (PreparedStatement statement =
                     getConnection().prepareStatement(FIND_ACCOUNT_BY_PHONE)) {
            statement.setInt(1, phone);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    account = createAccountFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(
                    "SQLException while finding by phone", e);
        }
        return account;
    }

    @Override
    public int findAccountCount() throws PersistenceException {
        final String FIND_ACCOUNT_COUNT = "SELECT COUNT(`id`) FROM `users`";
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_ACCOUNT_COUNT)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(
                    "SQLException while finding page accounts sorted by rating",
                    e);
        }
        return 0;
    }

    @Override
    public List<Account> findAll() throws PersistenceException {
        List<Account> list = new LinkedList<>();
        final String FIND_ALL_ACCOUNTS = "SELECT `id`, `username`, "
                + "`password`, `email`, `role`, `phone` FROM `users` ORDER BY "
                + "`username`";
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_ALL_ACCOUNTS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                list.add(createAccountFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new PersistenceException(
                    "SQLException while finding all accounts", e);
        }
        return list;
    }

    @Override
    public Account findEntityById(final long id) throws PersistenceException {
        Account account = null;
        final String FIND_ACCOUNT_BY_ID = "SELECT `id`, `username`, "
                + "`password`, `email`, `role`, `phone` FROM `users` WHERE "
                + "`id` = ?";
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_ACCOUNT_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    account = createAccountFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException("SQLException while finding by id",
                    e);
        }
        return account;
    }

    @Override
    public boolean delete(final long id) throws PersistenceException {
        String sql = "DELETE FROM `users` WHERE `id` = ?";
        try (PreparedStatement statement = getConnection()
                .prepareStatement(sql)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Account update(final Account entityNew) throws PersistenceException {
        final String UPDATE_ACCOUNT_BY_ID =
                "UPDATE `users` SET `username` = ?, `password` = ?, `email` ="
                        + " ?, `role` = ?, `phone` = ? WHERE `id` = ?";
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
            throw new PersistenceException("SQLException while updating", e);
        }
    }

    @Override
    public boolean create(final Account entityNew) throws PersistenceException {
        if (entityNew == null) {
            return false;
        }
        final String INSERT_ACCOUNT = "INSERT INTO `users` (`username`, "
                + "`password`, `email`, `role`, `phone`) "
                + "VALUES (?, ?, ?, ?, ?)";
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
            throw new PersistenceException(
                    "SQLException while inserting account", e);
        }
    }

    private Account createAccountFromResultSet(ResultSet resultSet)
            throws SQLException {
        System.out.println(resultSet);
        long accountId = resultSet.getLong(1);
        String username = resultSet.getString(2);
        String passwordHash = resultSet.getString(3);
        String email = resultSet.getString(4);
        Role role = Role.values()[resultSet.getInt(5)];
        int phone = resultSet.getInt(6);
        return new Account(accountId, username, passwordHash, role, email,
                phone);
    }
}
