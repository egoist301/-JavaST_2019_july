package by.training.catalog.dao.impl;

import by.training.catalog.bean.Form;
import by.training.catalog.bean.UnknownTypeException;
import by.training.catalog.dao.FormDao;
import by.training.catalog.dao.PersistentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static by.training.catalog.dao.Dao.LOGGER;

public class FormDaoImpl implements FormDao {
    private static final String FIND_FORM_BY_ID = "SELECT `name` FROM `form` "
            + "WHERE `id` = ?";
    private static final String FIND_FORM_COUNT = "SELECT COUNT(`id`) FROM "
            + "`form`";
    private static final String FIND_ALL_FORMS = "SELECT `name` FROM `form` "
            + "ORDER BY `id`";
    private static final String FIND_ALL_FORMS_PAGE = "SELECT `name`FROM "
            + "`form` ORDER BY `id` LIMIT ? OFFSET ?";
    private Connection connection;

    public FormDaoImpl(final Connection connectionNew) {
        connection = connectionNew;
    }

    @Override
    public Form findFormById(final long id) throws PersistentException {
        Form form = null;
        try (PreparedStatement statement = connection
                .prepareStatement(FIND_FORM_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    form = takeForm(resultSet);
                }
            } catch (UnknownTypeException eNew) {
                throw new PersistentException("Unknown type exception while "
                        + "finding by id", eNew);
            }
        } catch (SQLException e) {
            throw new PersistentException("SQLException while finding by id",
                    e);
        }
        return form;
    }

    @Override
    public List<Form> findAll() throws PersistentException {
        List<Form> list = new LinkedList<>();
        try (PreparedStatement statement = connection
                .prepareStatement(FIND_ALL_FORMS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                list.add(takeForm(resultSet));
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding all forms", e);
        } catch (UnknownTypeException eNew) {
            throw new PersistentException("Unknown type exception while find "
                    + "all forms", eNew);
        }
        return list;
    }

    @Override
    public List<Form> findAll(final int offset, final int limit)
            throws PersistentException {
        List<Form> forms = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(FIND_ALL_FORMS_PAGE)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    forms.add(takeForm(resultSet));
                }
            } catch (UnknownTypeException eNew) {
                throw new PersistentException(
                        "Unknown type exception while find "
                                + "all forms, offset, limit", eNew);
            }
            return forms;
        } catch (SQLException newE) {
            LOGGER.warn(newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public int findCountForms() throws PersistentException {
        try (PreparedStatement statement = connection
                .prepareStatement(FIND_FORM_COUNT)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding count of forms", e);
        }
        return 0;
    }

    private Form takeForm(final ResultSet resultSetNew)
            throws SQLException, UnknownTypeException {
        return Form.fromValue(resultSetNew.getString("name"));
    }
}
