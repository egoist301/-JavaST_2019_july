package by.training.catalog.dao.impl;

import by.training.catalog.bean.Form;
import by.training.catalog.bean.Manufacturer;
import by.training.catalog.bean.PlasticColor;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.dao.PersistenceException;
import by.training.catalog.dao.RubikDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class RubikDaoImpl extends AbstractDao<RubiksCube> implements RubikDao {
    RubikDaoImpl(final Connection connectionNew) {
        super(connectionNew);
    }

    @Override
    public List<RubiksCube> findAll() throws PersistenceException {
        List<RubiksCube> list = new LinkedList<>();
        final String FIND_ALL_RUBIKS =
                "SELECT `id`, `model`, `price`, `weight`, `info`, "
                        + "`primary_plastic`, `size`, `plastic_color_id`, "
                        + "`manufacturer_id`, `form_id`, `date_added` FROM "
                        + "`rubiks_cube` ORDER BY `model`";
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_ALL_RUBIKS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                list.add(createRubikFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new PersistenceException(
                    "SQLException while finding all rubiks", e);
        }
        return list;
    }

    @Override
    public RubiksCube findEntityById(final long id)
            throws PersistenceException {
        RubiksCube rubiksCube = null;
        final String FIND_RUBIK_BY_ID =
                "SELECT `id`, `model`, `price`, `weight`, `info`, "
                        + "`primary_plastic`, `size`, `plastic_color_id`, "
                        + "`manufacturer_id`, `form_id`, `date_added` FROM "
                        + "`rubiks_cube` WHERE `id` = ?";
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_RUBIK_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    rubiksCube = createRubikFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException("SQLException while finding by id",
                    e);
        }
        return rubiksCube;
    }

    @Override
    public RubiksCube update(final RubiksCube entityNew)
            throws PersistenceException {
        final String UPDATE_ACCOUNT_BY_ID =
                "UPDATE `rubiks_cube` SET `model` = ?, `price` = ?, `weight` ="
                        + " ?, `info` = ?, `primary_plastic` = ?, `size` = ?,"
                        + " `plastic_color_id` = ?, `manufacturer_id` = ?, "
                        + "`form_id` = ?, `date_added` = ? WHERE `id` = ?";
        try (PreparedStatement statement = getConnection()
                .prepareStatement(UPDATE_ACCOUNT_BY_ID)) {
            execute(statement, entityNew);
            statement.setLong(11, entityNew.getId());
            return entityNew;
        } catch (SQLException e) {
            throw new PersistenceException("SQLException while updating", e);
        }
    }

    @Override
    public boolean create(final RubiksCube entityNew)
            throws PersistenceException {
        if (entityNew == null) {
            return false;
        }
        final String INSERT_ACCOUNT = "INSERT INTO `rubiks_cube` (`model`, "
                + "`price`, `weight`, `info`, `primary_plastic`, `size`, "
                + "`plastic_color_id`, `manufacturer_id`, `form_id`, "
                + "`date_added`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection()
                .prepareStatement(INSERT_ACCOUNT)) {
            execute(statement, entityNew);
            int result = statement.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            throw new PersistenceException(
                    "SQLException while inserting rubik", e);
        }
    }

    @Override
    public boolean delete(final long id) throws PersistenceException {
        String sql = "DELETE FROM `rubiks_cube` WHERE `id` = ?";
        try (PreparedStatement statement = getConnection()
                .prepareStatement(sql)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    private void execute(PreparedStatement statement, RubiksCube entityNew)
            throws SQLException {
        statement.setString(1, entityNew.getModel());
        statement.setDouble(2, entityNew.getPrice());
        statement.setDouble(3, entityNew.getWeight());
        statement.setString(4, entityNew.getInfo());
        statement.setBoolean(5, entityNew.isPrimaryPlastic());
        statement.setString(6, entityNew.getSize());
        statement.setInt(7, entityNew.getPlasticColor().getIdentity());
        statement.setInt(8, entityNew.getManufacturer().getIdentity());
        statement.setInt(9, entityNew.getForm().getIdentity());
        statement.setDate(10, new Date(entityNew.getDate().getTime()));
    }

    private RubiksCube createRubikFromResultSet(ResultSet resultSet)
            throws SQLException {
        long accountId = resultSet.getLong(1);
        String model = resultSet.getString(2);
        double price = resultSet.getDouble(3);
        double weight = resultSet.getDouble(6);
        String info = resultSet.getString(4);
        boolean primaryPlastic = resultSet.getBoolean(8);
        String size = resultSet.getString(9);
        PlasticColor plasticColor = PlasticColor.values()[resultSet.getInt(7)];
        Manufacturer manufacturer = Manufacturer.values()[resultSet.getInt(5)];
        Form form = Form.values()[resultSet.getInt(10)];
        Date date = new Date(resultSet.getDate(11).getTime());
        return new RubiksCube(accountId, model, price, weight, info,
                primaryPlastic, size, plasticColor, manufacturer, form, date);
    }

    @Override
    public List<RubiksCube> findRubiksBySize(final String size)
            throws PersistenceException {
        return null;
    }

    @Override
    public RubiksCube findRubikByModel(final String model)
            throws PersistenceException {
        if (model == null) {
            return null;
        }
        final String FIND_RUBIK_BY_MODEL =
                "SELECT `id`, `model`, `price`, `weight`, `info`, "
                        + "`primary_plastic`, `size`, `plastic_color_id`, "
                        + "`manufacturer_id`, `form_id`, `date_added` FROM "
                        + "`rubiks_cube` WHERE `model` = ?";
        RubiksCube rubiksCube = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_RUBIK_BY_MODEL)) {
            statement.setString(1, model);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    rubiksCube = createRubikFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException("SQLException while finding by "
                    + "model", e);
        }
        return rubiksCube;
    }

    @Override
    public List<RubiksCube> findRubiksByRangePrice(final int minPrice,
                                                   final int maxPrice)
            throws PersistenceException {
        return null;
    }

    @Override
    public List<RubiksCube> findRubiksByForm(final Form form)
            throws PersistenceException {
        return null;
    }

    @Override
    public int findRubiksCount() throws PersistenceException {
        final String FIND_RUBIK_COUNT = "SELECT COUNT(`id`) FROM`rubiks_cube`";
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_RUBIK_COUNT)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(
                    "SQLException while finding rubiks count", e);
        }
        return 0;
    }
}
