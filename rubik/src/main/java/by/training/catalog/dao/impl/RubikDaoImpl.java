package by.training.catalog.dao.impl;

import by.training.catalog.bean.Form;
import by.training.catalog.bean.Manufacturer;
import by.training.catalog.bean.PlasticColor;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.dao.PersistentException;
import by.training.catalog.dao.RubikDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class RubikDaoImpl extends AbstractDao<RubiksCube> implements RubikDao {
    private static final String FIND_PLASTIC_COLOR = "SELECT `plastic_color` "
            + "FROM `plastic_color` JOIN `rubiks_cube` ON "
            + "`id`=`plastic_color_id` WHERE `rubiks_cube.id` = ?";
    private static final String FIND_RUBIK_BY =
            "SELECT `id`, `model`, `price`, `weight`, `info`, "
                    + "`primary_plastic`, `size`, `plastic_color_id`, "
                    + "`manufacturer_id`, `form_id`, `date_added` FROM "
                    + "`rubiks_cube` WHERE ";
    private static final String FIND_ALL_RUBIKS =
            "SELECT `id`, `model`, `price`, `weight`, `info`, "
                    + "`primary_plastic`, `size`, `plastic_color_id`, "
                    + "`manufacturer_id`, `form_id`, `date_added` FROM "
                    + "`rubiks_cube` ORDER BY `model`";
    private static final String FIND_RUBIK_BY_ID = FIND_RUBIK_BY + "`id` = ?";
    private static final String UPDATE_RUBIK_BY_ID =
            "UPDATE `rubiks_cube` SET `model` = ?, `price` = ?, `weight` ="
                    + " ?, `info` = ?, `primary_plastic` = ?, `size` = ?,"
                    + " `plastic_color_id` = ?, `manufacturer_id` = ?, "
                    + "`form_id` = ?, `date_added` = ? WHERE `id` = ?";
    private static final String INSERT_RUBIK =
            "INSERT INTO `rubiks_cube` (`model`, "
                    + "`price`, `weight`, `info`, `primary_plastic`, `size`, "
                    + "`plastic_color_id`, `manufacturer_id`, `form_id`, "
                    + "`date_added`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_RUBIK_BY_ID =
            "DELETE FROM `rubiks_cube` WHERE `id` = ?";
    private static final String FIND_RUBIK_BY_MODEL = FIND_RUBIK_BY + "`model`"
            + " = ?";
    private static final String FIND_RUBIK_COUNT =
            "SELECT COUNT(`id`) FROM`rubiks_cube`";
    private static final String FIND_ALL_RUBIKS_PAGE = "SELECT `id`, `model`,"
            + " `price`, `weight`, `info`, `primary_plastic`, `size`, "
            + "`plastic_color_id`, `manufacturer_id`, `form_id`, `date_added`"
            + " FROM `rubiks_cube` ORDER BY id LIMIT ? OFFSET ?";
    private static final String FIND_ALL_RUBIKS_BY_SIZE = FIND_RUBIK_BY +
            "`size` = ? LIMIT ? OFFSET ? ";
    private static final String FIND_ALL_RUBIKS_BY_PRICE = FIND_RUBIK_BY +
            "`price` BETWEEN ? AND ? LIMIT ? OFFSET ?";
    private static final String FIND_ALL_RUBIKS_BY_FORM = FIND_RUBIK_BY +
            "`form_id` = ? LIMIT ? OFFSET ?";

    public RubikDaoImpl(final Connection connectionNew) {
        super(connectionNew);
    }

    @Override
    public List<RubiksCube> findAll() throws PersistentException {
        List<RubiksCube> list = new LinkedList<>();
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_ALL_RUBIKS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                list.add(createRubikFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding all rubiks", e);
        }
        return list;
    }

    @Override
    public List<RubiksCube> findAll(final int offset, final int limit)
            throws PersistentException {
        List<RubiksCube> coupons = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_ALL_RUBIKS_PAGE)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    coupons.add(createRubikFromResultSet(resultSet));
                }
            }
            return coupons;
        } catch (SQLException newE) {
            LOGGER.warn(newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public RubiksCube findEntityById(final long id)
            throws PersistentException {
        RubiksCube rubiksCube = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_RUBIK_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    rubiksCube = createRubikFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new PersistentException("SQLException while finding by id",
                    e);
        }
        return rubiksCube;
    }

    @Override
    public RubiksCube update(final RubiksCube entityNew)
            throws PersistentException {
        try (PreparedStatement statement = getConnection()
                .prepareStatement(UPDATE_RUBIK_BY_ID)) {
            execute(statement, entityNew);
            statement.setLong(11, entityNew.getId());
            return entityNew;
        } catch (SQLException e) {
            throw new PersistentException("SQLException while updating", e);
        }
    }

    @Override
    public boolean create(final RubiksCube entityNew)
            throws PersistentException {
        if (entityNew == null) {
            return false;
        }
        try (PreparedStatement statement = getConnection()
                .prepareStatement(INSERT_RUBIK)) {
            execute(statement, entityNew);
            int result = statement.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while inserting rubik", e);
        }
    }

    @Override
    public boolean delete(final long id) throws PersistentException {
        try (PreparedStatement statement = getConnection()
                .prepareStatement(DELETE_RUBIK_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new PersistentException(e);
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
        statement.setInt(7, entityNew.getPlasticColorId());
        statement.setInt(8, entityNew.getManufacturerId());
        statement.setInt(9, entityNew.getFormId());
        statement.setDate(10, new Date(entityNew.getDate().getTime()));
    }

    private RubiksCube createRubikFromResultSet(ResultSet resultSet)
            throws SQLException {
        long accountId = resultSet.getLong(1);
        String model = resultSet.getString(2);
        double price = resultSet.getDouble(3);
        double weight = resultSet.getDouble(4);
        String info = resultSet.getString(5);
        boolean primaryPlastic = resultSet.getBoolean(6);
        String size = resultSet.getString(7);
        int plasticColor = resultSet.getInt(8);
        int manufacturer = resultSet.getInt(9);
        int form = resultSet.getInt(10);
        Date date = new Date(resultSet.getDate(11).getTime());
        return new RubiksCube(accountId, model, price, weight, info,
                primaryPlastic, size, plasticColor, manufacturer, form, date);
    }

    @Override
    public List<RubiksCube> findRubiksBySize(final String size,
                                             final int offset, final int limit)
            throws PersistentException {
        List<RubiksCube> coupons = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_ALL_RUBIKS_BY_SIZE)) {
            preparedStatement.setString(1, size);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    coupons.add(createRubikFromResultSet(resultSet));
                }
            }
            return coupons;
        } catch (SQLException newE) {
            LOGGER.warn(newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public RubiksCube findRubikByModel(final String model)
            throws PersistentException {
        if (model == null) {
            return null;
        }
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
            throw new PersistentException("SQLException while finding by "
                    + "model", e);
        }
        return rubiksCube;
    }

    @Override
    public List<RubiksCube> findRubiksByRangePrice(final int minPrice,
                                                   final int maxPrice,
                                                   final int offset,
                                                   final int limit)
            throws PersistentException {
        List<RubiksCube> coupons = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_ALL_RUBIKS_BY_PRICE)) {
            preparedStatement.setInt(1, minPrice);
            preparedStatement.setInt(2, maxPrice);
            preparedStatement.setInt(3, limit);
            preparedStatement.setInt(4, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    coupons.add(createRubikFromResultSet(resultSet));
                }
            }
            return coupons;
        } catch (SQLException newE) {
            LOGGER.warn(newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<RubiksCube> findRubiksByForm(final Form form, final int offset,
                                             final int limit)
            throws PersistentException {
        List<RubiksCube> coupons = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_ALL_RUBIKS_BY_FORM)) {
            preparedStatement.setInt(1, form.ordinal() + 1);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    coupons.add(createRubikFromResultSet(resultSet));
                }
            }
            return coupons;
        } catch (SQLException newE) {
            LOGGER.warn(newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public int findRubiksCount() throws PersistentException {
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_RUBIK_COUNT)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding rubiks count", e);
        }
        return 0;
    }
}
