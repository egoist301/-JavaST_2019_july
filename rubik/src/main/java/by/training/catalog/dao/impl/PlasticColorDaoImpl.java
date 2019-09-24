package by.training.catalog.dao.impl;

import by.training.catalog.bean.PlasticColor;
import by.training.catalog.bean.UnknownTypeException;
import by.training.catalog.dao.PersistentException;
import by.training.catalog.dao.PlasticColorDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static by.training.catalog.dao.Dao.LOGGER;

public class PlasticColorDaoImpl implements PlasticColorDao {
    private static final String FIND_COLOR_BY_ID = "SELECT "
            + "`plastic_color` FROM `plastic_color` WHERE `id` = ?";
    private static final String FIND_COLOR_COUNT = "SELECT COUNT(`id`) "
            + "FROM `plastic_color`";
    private static final String FIND_ALL_COLORS = "SELECT "
            + "`plastic_color` FROM `plastic_color` ORDER BY `id`";
    private static final String FIND_ALL_COLORS_PAGE = "SELECT "
            + "`plastic_color`FROM `plastic_color` ORDER BY `id` LIMIT ? "
            + "OFFSET ?";

    private Connection connection;

    public PlasticColorDaoImpl(
            final AbstractConnectionManager connectionManagerNew) {
        connection = connectionManagerNew.getConnection();
    }

    @Override
    public PlasticColor findPlasticColorById(final long id)
            throws PersistentException {
        PlasticColor plasticColor = null;
        try (PreparedStatement statement = connection
                .prepareStatement(FIND_COLOR_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    plasticColor = takePlasticColor(resultSet);
                }
            } catch (UnknownTypeException eNew) {
                throw new PersistentException("Unknown type exception while "
                        + "finding by id", eNew);
            }
        } catch (SQLException e) {
            throw new PersistentException("SQLException while finding by id",
                    e);
        }
        return plasticColor;
    }

    @Override
    public List<PlasticColor> findAll() throws PersistentException {
        List<PlasticColor> list = new LinkedList<>();
        try (PreparedStatement statement = connection
                .prepareStatement(FIND_ALL_COLORS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                list.add(takePlasticColor(resultSet));
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding all plastic colors", e);
        } catch (UnknownTypeException eNew) {
            throw new PersistentException("Unknown type exception while find "
                    + "all plastic colors", eNew);
        }
        return list;
    }

    @Override
    public List<PlasticColor> findAll(final int offset, final int limit)
            throws PersistentException {
        List<PlasticColor> plasticColors = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(FIND_ALL_COLORS_PAGE)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    plasticColors.add(takePlasticColor(resultSet));
                }
            } catch (UnknownTypeException eNew) {
                throw new PersistentException(
                        "Unknown type exception while find "
                                + "all plastic colors, offset, limit", eNew);
            }
            return plasticColors;
        } catch (SQLException newE) {
            LOGGER.warn(newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public int findCountColors() throws PersistentException {
        try (PreparedStatement statement = connection
                .prepareStatement(FIND_COLOR_COUNT)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding count of plastic colors",
                    e);
        }
        return 0;
    }

    private PlasticColor takePlasticColor(final ResultSet resultSetNew)
            throws SQLException, UnknownTypeException {
        return PlasticColor.fromValue(resultSetNew.getString("plastic_color"));
    }
}
