package by.training.catalog.dao.impl;

import by.training.catalog.bean.Manufacturer;
import by.training.catalog.bean.UnknownTypeException;
import by.training.catalog.dao.ManufacturerDao;
import by.training.catalog.dao.PersistentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static by.training.catalog.dao.Dao.LOGGER;

public class ManufacturerDaoImpl implements ManufacturerDao {
    private static final String FIND_MANUFACTURER_BY_ID = "SELECT "
            + "`name_manufacturer` FROM `manufacturer` WHERE `id` = ?";
    private static final String FIND_MANUFACTURER_COUNT = "SELECT COUNT(`id`) "
            + "FROM `manufacturer`";
    private static final String FIND_ALL_MANUFACTURERS = "SELECT "
            + "`name_manufacturer` FROM `manufacturer` ORDER BY `id`";
    private static final String FIND_ALL_MANUFACTURERS_PAGE = "SELECT "
            + "`name_manufacturer`FROM `manufacturer` ORDER BY `id` LIMIT ? "
            + "OFFSET ?";

    private Connection connection;

    public ManufacturerDaoImpl(final AbstractConnectionManager connectionManagerNew) {
        connection = connectionManagerNew.getConnection();
    }

    @Override
    public Manufacturer findManufacturerById(final long id)
            throws PersistentException {
        Manufacturer manufacturer = null;
        try (PreparedStatement statement = connection
                .prepareStatement(FIND_MANUFACTURER_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    manufacturer = takeManufacturer(resultSet);
                }
            } catch (UnknownTypeException eNew) {
                throw new PersistentException("Unknown type exception while "
                        + "finding by id", eNew);
            }
        } catch (SQLException e) {
            throw new PersistentException("SQLException while finding by id",
                    e);
        }
        return manufacturer;
    }

    @Override
    public List<Manufacturer> findAll() throws PersistentException {
        List<Manufacturer> list = new LinkedList<>();
        try (PreparedStatement statement = connection
                .prepareStatement(FIND_ALL_MANUFACTURERS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                list.add(takeManufacturer(resultSet));
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding all manufacturers", e);
        } catch (UnknownTypeException eNew) {
            throw new PersistentException("Unknown type exception while find "
                    + "all manufacturers", eNew);
        }
        return list;
    }

    @Override
    public List<Manufacturer> findAll(final int offset, final int limit)
            throws PersistentException {
        List<Manufacturer> manufacturers = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(FIND_ALL_MANUFACTURERS_PAGE)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    manufacturers.add(takeManufacturer(resultSet));
                }
            } catch (UnknownTypeException eNew) {
                throw new PersistentException(
                        "Unknown type exception while find "
                                + "all manufacturers, offset, limit", eNew);
            }
            return manufacturers;
        } catch (SQLException newE) {
            LOGGER.warn(newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public int findCountManufacturers() throws PersistentException {
        try (PreparedStatement statement = connection
                .prepareStatement(FIND_MANUFACTURER_COUNT)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding count of manufacturers",
                    e);
        }
        return 0;
    }

    private Manufacturer takeManufacturer(final ResultSet resultSetNew)
            throws SQLException, UnknownTypeException {
        return Manufacturer.fromValue(resultSetNew.getString(
                "name_manufacturer"));
    }
}
