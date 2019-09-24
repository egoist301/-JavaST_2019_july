package by.training.catalog.dao.impl;

import by.training.catalog.bean.Form;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.dao.PersistentException;
import by.training.catalog.dao.RubikDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class RubikDaoImpl extends AbstractDao<RubiksCube> implements RubikDao {
    private static final String FIND_RUBIK_BY =
            "SELECT `id`, `model`, `price`, `weight`, `info`, "
                    + "`primary_plastic`, `size`, `plastic_color_id`, "
                    + "`manufacturer_id`, `form_id`, `date_added`, `blocked`"
                    + " FROM `rubiks_cube` WHERE ";
    private static final String FIND_ALL_RUBIKS =
            "SELECT `id`, `model`, `price`, `weight`, `info`, "
                    + "`primary_plastic`, `size`, `plastic_color_id`, "
                    + "`manufacturer_id`, `form_id`, `date_added`, `blocked`"
                    + " FROM `rubiks_cube` ORDER BY `model`";
    private static final String FIND_RUBIK_BY_ID = FIND_RUBIK_BY + "`id` = ?";
    private static final String UPDATE_RUBIK_BY_ID =
            "UPDATE `rubiks_cube` SET `model` = ?, `price` = ?, `weight` ="
                    + " ?, `info` = ?, `primary_plastic` = ?, `size` = ?,"
                    + " `plastic_color_id` = ?, `manufacturer_id` = ?, "
                    + "`form_id` = ?, `date_added` = ?, `blocked` = ? WHERE "
                    + "`id` = ?";
    private static final String INSERT_RUBIK =
            "INSERT INTO `rubiks_cube` (`model`, "
                    + "`price`, `weight`, `info`, `primary_plastic`, `size`, "
                    + "`plastic_color_id`, `manufacturer_id`, `form_id`, "
                    + "`date_added`, `blocked`) VALUES (?, ?, ?, ?, ?, ?, ?, "
                    + "?,  ?, ?, ?)";
    private static final String FIND_RUBIK_BY_MODEL = FIND_RUBIK_BY + "`model`"
            + " = ?";
    private static final String FIND_RUBIK_COUNT =
            "SELECT COUNT(`id`) FROM`rubiks_cube`";
    private static final String FIND_ALL_RUBIKS_PAGE = "SELECT `id`, `model`,"
            + " `price`, `weight`, `info`, `primary_plastic`, `size`, "
            + "`plastic_color_id`, `manufacturer_id`, `form_id`, `date_added`"
            + ", `blocked` FROM `rubiks_cube` ORDER BY id LIMIT ? OFFSET ?";
    private static final String FIND_ALL_RUBIKS_BY_SIZE = FIND_RUBIK_BY
            + "`size` = ? LIMIT ? OFFSET ? ";
    private static final String FIND_ALL_RUBIKS_BY_PRICE = FIND_RUBIK_BY
            + "`price` BETWEEN ? AND ? LIMIT ? OFFSET ?";
    private static final String FIND_ALL_RUBIKS_BY_FORM = FIND_RUBIK_BY
            + "`form_id` = ? LIMIT ? OFFSET ?";

    RubikDaoImpl(final AbstractConnectionManager managerNew) {
        super(managerNew);
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
        List<RubiksCube> rubiksCubes = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_ALL_RUBIKS_PAGE)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    rubiksCubes.add(createRubikFromResultSet(resultSet));
                }
            }
            return rubiksCubes;
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
    public void update(final RubiksCube entityNew)
            throws PersistentException {
        try (PreparedStatement statement = getConnection()
                .prepareStatement(UPDATE_RUBIK_BY_ID)) {
            execute(statement, entityNew);
            statement.setLong(11, entityNew.getId());
        } catch (SQLException e) {
            throw new PersistentException("SQLException while updating", e);
        }
    }

    @Override
    public int create(final RubiksCube entityNew)
            throws PersistentException {
        if (entityNew == null) {
            return 0;
        }
        try (PreparedStatement statement = getConnection()
                .prepareStatement(INSERT_RUBIK,
                        RETURN_GENERATED_KEYS)) {
            execute(statement, entityNew);
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while inserting rubik", e);
        }
        return 0;
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
        long accountId = resultSet.getLong("id");
        String model = resultSet.getString("model");
        double price = resultSet.getDouble("price");
        double weight = resultSet.getDouble("weight");
        String info = resultSet.getString("info");
        boolean primaryPlastic = resultSet.getBoolean("primary_plastic");
        String size = resultSet.getString("size");
        int plasticColor = resultSet.getInt("plastic_color_id");
        int manufacturer = resultSet.getInt("manufacturer_id");
        int form = resultSet.getInt("form_id");
        Date date = new Date(resultSet.getDate("date_added").getTime());
        boolean blocked = resultSet.getBoolean("blocked");
        return new RubiksCube(accountId, model, price, weight, info,
                primaryPlastic, size, plasticColor, manufacturer, form, date,
                blocked);
    }

    @Override
    public List<RubiksCube> findRubiksBySize(final String size,
                                             final int offset, final int limit)
            throws PersistentException {
        List<RubiksCube> rubiksCubes = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_ALL_RUBIKS_BY_SIZE)) {
            preparedStatement.setString(1, size);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    rubiksCubes.add(createRubikFromResultSet(resultSet));
                }
            }
            return rubiksCubes;
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
        List<RubiksCube> rubiksCubes = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_ALL_RUBIKS_BY_PRICE)) {
            preparedStatement.setInt(1, minPrice);
            preparedStatement.setInt(2, maxPrice);
            preparedStatement.setInt(3, limit);
            preparedStatement.setInt(4, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    rubiksCubes.add(createRubikFromResultSet(resultSet));
                }
            }
            return rubiksCubes;
        } catch (SQLException newE) {
            LOGGER.warn(newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<RubiksCube> findRubiksByForm(final Form form, final int offset,
                                             final int limit)
            throws PersistentException {
        List<RubiksCube> rubiksCubes = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_ALL_RUBIKS_BY_FORM)) {
            preparedStatement.setInt(1, form.ordinal() + 1);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    rubiksCubes.add(createRubikFromResultSet(resultSet));
                }
            }
            return rubiksCubes;
        } catch (SQLException newE) {
            LOGGER.warn(newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public int findElementCount() throws PersistentException {
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
