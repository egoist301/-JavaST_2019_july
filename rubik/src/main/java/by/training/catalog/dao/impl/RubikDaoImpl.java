package by.training.catalog.dao.impl;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.PersistentException;
import by.training.catalog.dao.RubikDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class RubikDaoImpl extends AbstractDao<RubiksCube> implements RubikDao {
    private static final String JOIN_RUBIK_WITH_TABLES =
            "JOIN manufacturer m on rubiks_cube.manufacturer_id = m.id JOIN "
                    + "form f on rubiks_cube.form_id = f.id JOIN "
                    + "plastic_color pc on rubiks_cube.plastic_color_id = pc"
                    + ".id ";
    private static final String FIND_RUBIK_BY =
            "SELECT `rubiks_cube`.`id`, `model`, `price`, `weight`, `info`, "
                    + "`primary_plastic`, `size`, `plastic_color`, "
                    + "`name_manufacturer`, `name`, `date_added`, `blocked`"
                    + " FROM `rubiks_cube` " + JOIN_RUBIK_WITH_TABLES;
    private static final String FIND_ALL_RUBIKS =
            "SELECT `rubiks_cube`.`id`, `model`, `price`, `weight`, `info`, "
                    + "`primary_plastic`, `size`, `plastic_color`, "
                    + "`name_manufacturer`, `name`, `date_added`, `blocked`"
                    + " FROM `rubiks_cube` " + JOIN_RUBIK_WITH_TABLES + " ORDER"
                    + " BY `model`";
    private static final String FIND_RUBIK_BY_ID = FIND_RUBIK_BY
            + "WHERE `rubiks_cube`.`id` = ?";
    private static final String UPDATE_RUBIK_BY_ID =
            "UPDATE `rubiks_cube`" + JOIN_RUBIK_WITH_TABLES + " SET `model` = "
                    + "?, `price` = ?, `weight` ="
                    + " ?, `info` = ?, `primary_plastic` = ?, `size` = ?,"
                    + " `plastic_color` = ?, `name_manufacturer` = ?, "
                    + "`name` = ?, `date_added` = ?, `blocked` = ? WHERE "
                    + "`rubiks_cube`.`id` = ?";
    private static final String INSERT_RUBIK =
            "INSERT INTO `rubiks_cube` (`model`, "
                    + "`price`, `weight`, `info`, `primary_plastic`, `size`, "
                    + "`plastic_color_id`, `manufacturer_id`, `form_id`, "
                    + "`date_added`, `blocked`) VALUES (?, ?, ?, ?, ?, ?, "
                    + "?, ?,  ?, ?, ?)";
    private static final String FIND_RUBIK_BY_MODEL = FIND_RUBIK_BY
            + " WHERE `model` = ?";
    private static final String FIND_RUBIK_COUNT =
            "SELECT COUNT(`id`) FROM`rubiks_cube`";
    private static final String FIND_ALL_RUBIKS_PAGE = "SELECT `rubiks_cube`."
            + "`id`, `model`,"
            + " `price`, `weight`, `info`, `primary_plastic`, `size`, "
            + "`plastic_color`, `name_manufacturer`, `name`, `date_added`"
            + ", `blocked` FROM `rubiks_cube` " + JOIN_RUBIK_WITH_TABLES
            + " ORDER BY id LIMIT ? OFFSET ?";
    private static final String FIND_ALL_RUBIKS_BY_SIZE = FIND_RUBIK_BY
            + " WHERE `size` LIKE ? LIMIT ? OFFSET ? ";
    private static final String FIND_ALL_RUBIKS_BY_PRICE = FIND_RUBIK_BY
            + " WHERE `price` BETWEEN ? AND ? LIMIT ? OFFSET ?";
    private static final String FIND_ALL_RUBIKS_BY_FORM = FIND_RUBIK_BY
            + " WHERE `name` LIKE ? LIMIT ? OFFSET ?";
    private static final String FILL_RUBIK =
            "SELECT `model`, `price`, `weight`, `info`, `primary_plastic`, "
                    + "`size`, `plastic_color`, `name_manufacturer`, "
                    + "`name`, `date_added`, `blocked` FROM `rubiks_cube` "
                    + JOIN_RUBIK_WITH_TABLES + "WHERE `rubiks_cube`.`id` = ?";
    private static final String READ_ALL_MANUFACTURERS =
            "SELECT `name_manufacturer` FROM `manufacturer`";
    private static final String READ_ALL_FORMS = "SELECT `name` FROM `form`";
    private static final String READ_ALL_COLORS =
            "SELECT `plastic_color` FROM `plastic_color`";

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
            statement.setLong(12, entityNew.getId());
            statement.executeUpdate();
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

    @Override
    public List<RubiksCube> findRubiksBySize(final String size,
                                             final int offset, final int limit)
            throws PersistentException {
        return getCubes(size, offset, limit, FIND_ALL_RUBIKS_BY_SIZE);
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
    public void read(final RubiksCube rubiksCubeNew)
            throws PersistentException {
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(FILL_RUBIK)) {
            preparedStatement.setLong(1, rubiksCubeNew.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    rubiksCubeNew.setModel(resultSet.getString("model"));
                    rubiksCubeNew.setPrice(resultSet.getDouble("price"));
                    rubiksCubeNew.setWeight(resultSet.getDouble("weight"));
                    rubiksCubeNew.setInfo(resultSet.getString("info"));
                    rubiksCubeNew.setPrimaryPlastic(resultSet.getBoolean(
                            "primary_plastic"));
                    rubiksCubeNew.setSize(resultSet.getString("size"));
                    rubiksCubeNew.setPlasticColor(resultSet.getString(
                            "plastic_color"));
                    rubiksCubeNew.setManufacturer(resultSet.getString(
                            "name_manufacturer"));
                    rubiksCubeNew.setForm(resultSet.getString("name"));
                    rubiksCubeNew.setDate(resultSet.getDate("date_added"));
                    rubiksCubeNew.setBlocked(resultSet.getBoolean("blocked"));
                }
            }
        } catch (SQLException eNew) {
            throw new PersistentException(eNew);
        }
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
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<RubiksCube> findRubiksByForm(final String form,
                                             final int offset,
                                             final int limit)
            throws PersistentException {
        return getCubes(form, offset, limit, FIND_ALL_RUBIKS_BY_FORM);
    }

    private List<RubiksCube> getCubes(final String form, final int offset,
                                      final int limit,
                                      final String findAllRubiksBy)
            throws PersistentException {
        List<RubiksCube> rubiksCubes = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(findAllRubiksBy)) {
            preparedStatement.setString(1, form + '%');
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    rubiksCubes.add(createRubikFromResultSet(resultSet));
                }
            }
            return rubiksCubes;
        } catch (SQLException newE) {
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<String> readAllManufacturer() throws PersistentException {
        List<String> manufacturers = new ArrayList<>();
        try (PreparedStatement statement =
                     getConnection().prepareStatement(READ_ALL_MANUFACTURERS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                manufacturers.add(resultSet.getString("name_manufacturer"));
            }
            return manufacturers;
        } catch (SQLException eNew) {
            throw new PersistentException(eNew);
        }
    }

    @Override
    public List<String> readAllForm() throws PersistentException {
        List<String> forms = new ArrayList<>();
        try (PreparedStatement statement =
                     getConnection().prepareStatement(READ_ALL_FORMS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                forms.add(resultSet.getString("name"));
            }
            return forms;
        } catch (SQLException eNew) {
            throw new PersistentException(eNew);
        }
    }

    @Override
    public List<String> readAllPlasticColor() throws PersistentException {
        List<String> colors = new ArrayList<>();
        try (PreparedStatement statement =
                     getConnection().prepareStatement(READ_ALL_COLORS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                colors.add(resultSet.getString("plastic_color"));
            }
            return colors;
        } catch (SQLException eNew) {
            throw new PersistentException(eNew);
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

    private void execute(final PreparedStatement statement,
                         final RubiksCube entityNew)
            throws SQLException {
        statement.setString(1, entityNew.getModel());
        statement.setDouble(2, entityNew.getPrice());
        statement.setDouble(3, entityNew.getWeight());
        statement.setString(4, entityNew.getInfo());
        statement.setBoolean(5, entityNew.isPrimaryPlastic());
        statement.setString(6, entityNew.getSize());
        statement.setString(7, entityNew.getPlasticColor());
        statement.setString(8, entityNew.getManufacturer());
        statement.setString(9, entityNew.getForm());
        statement.setDate(10, new Date(entityNew.getDate().getTime()));
        statement.setBoolean(11, entityNew.isBlocked());
    }

    private RubiksCube createRubikFromResultSet(final ResultSet resultSet)
            throws SQLException {
        long accountId = resultSet.getLong("id");
        String model = resultSet.getString("model");
        double price = resultSet.getDouble("price");
        double weight = resultSet.getDouble("weight");
        String info = resultSet.getString("info");
        boolean primaryPlastic = resultSet.getBoolean("primary_plastic");
        String size = resultSet.getString("size");
        String plasticColor = resultSet.getString("plastic_color");
        String manufacturer = resultSet.getString("name_manufacturer");
        String form = resultSet.getString("name");
        Date date = new Date(resultSet.getDate("date_added").getTime());
        boolean blocked = resultSet.getBoolean("blocked");
        return new RubiksCube(accountId, model, price, weight, info,
                primaryPlastic, size, plasticColor, manufacturer, form, date,
                blocked);
    }
}
