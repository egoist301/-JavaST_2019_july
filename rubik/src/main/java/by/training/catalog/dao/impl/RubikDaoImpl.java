package by.training.catalog.dao.impl;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.PersistenceException;
import by.training.catalog.dao.RubikDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * Rubik dao implementation.
 */
public class RubikDaoImpl extends AbstractDao<RubiksCube> implements RubikDao {
    /**
     * Join rubik with other tables. SQL query.
     */
    private static final String JOIN_RUBIK_WITH_TABLES =
            "JOIN manufacturer m on rubiks_cube.manufacturer_id = m.id JOIN "
                    + "form f on rubiks_cube.form_id = f.id JOIN "
                    + "plastic_color pc on rubiks_cube.plastic_color_id = pc"
                    + ".id ";
    /**
     * Count rubik's by... SQL query.
     */
    private static final String COUNT_RUBIK_BY =
            "SELECT COUNT(rubiks_cube.`id`) FROM "
                    + "`rubiks_cube` " + JOIN_RUBIK_WITH_TABLES;
    /**
     * Find rubik by... SQL query.
     */
    private static final String FIND_RUBIK_BY =
            "SELECT `rubiks_cube`.`id`, `model`, `price`, `weight`, `info`, "
                    + "`primary_plastic`, `size`, `plastic_color`, "
                    + "`name_manufacturer`, `name`, `date_added`, `blocked`"
                    + " FROM `rubiks_cube` " + JOIN_RUBIK_WITH_TABLES;
    /**
     * Find rubik by id. SQL query.
     */
    private static final String FIND_RUBIK_BY_ID = FIND_RUBIK_BY
            + "WHERE `rubiks_cube`.`id` = ?";
    /**
     * Update rubik info by id. SQL query.
     */
    private static final String UPDATE_RUBIK_BY_ID =
            "UPDATE `rubiks_cube` SET `model` = ?, `price` = ?, `weight` ="
                    + " ?, `info` = ?, `primary_plastic` = ?, `size` = ?,"
                    + " `plastic_color_id` = (SELECT plastic_color.id FROM "
                    + "plastic_color WHERE plastic_color.plastic_color = ?), "
                    + "`manufacturer_id` = (SELECT manufacturer.id FROM  "
                    + "manufacturer WHERE name_manufacturer = ?), "
                    + "`form_id` = (SELECT form.id FROM form WHERE name = ?), "
                    + "`date_added` = ? WHERE `rubiks_cube`.`id` = ?";
    /**
     * Insert rubik. SQL query.
     */
    private static final String INSERT_RUBIK =
            "INSERT INTO `rubiks_cube` (`model`, "
                    + "`price`, `weight`, `info`, `primary_plastic`, "
                    + "`size`, "
                    + "`plastic_color_id`, `manufacturer_id`, `form_id`, "
                    + "`date_added`, `blocked`) VALUES (?, ?, ?, ?, ?, ?, "
                    + "(SELECT plastic_color.id FROM  plastic_color WHERE "
                    + "plastic_color = ?), "
                    + "(SELECT manufacturer.id FROM  manufacturer WHERE "
                    + "name_manufacturer = ?), "
                    + "(SELECT form.id FROM form WHERE name = ?), ?, "
                    + "false)";
    /**
     * Find rubik by model. SQL query.
     */
    private static final String FIND_RUBIK_BY_MODEL = FIND_RUBIK_BY
            + " WHERE `model` LIKE ? LIMIT ? OFFSET ?";
    /**
     * Find count of rubik. SQL query.
     */
    private static final String FIND_RUBIK_COUNT =
            "SELECT COUNT(`id`) FROM`rubiks_cube`";
    /**
     * Find all rubik's page. SQL query.
     */
    private static final String FIND_ALL_RUBIKS_PAGE = "SELECT `rubiks_cube`."
            + "`id`, `model`,"
            + " `price`, `weight`, `info`, `primary_plastic`, `size`, "
            + "`plastic_color`, `name_manufacturer`, `name`, `date_added`"
            + ", `blocked` FROM `rubiks_cube` " + JOIN_RUBIK_WITH_TABLES
            + " ORDER BY id LIMIT ? OFFSET ?";
    /**
     * Find all rubik's by size. SQL query.
     */
    private static final String FIND_ALL_RUBIKS_BY_SIZE = FIND_RUBIK_BY
            + " WHERE `size` LIKE ? LIMIT ? OFFSET ?";
    private static final String COUNT_RUBIK_BY_SIZE = COUNT_RUBIK_BY
            + " WHERE size LIKE ?";
    /**
     * Find all rubik's by price. SQL query.
     */
    private static final String FIND_ALL_RUBIKS_BY_PRICE = FIND_RUBIK_BY
            + " WHERE `price` BETWEEN ? AND ? LIMIT ? OFFSET ?";
    /**
     * Find all rubik's by form. SQL query.
     */
    private static final String FIND_ALL_RUBIKS_BY_FORM = FIND_RUBIK_BY
            + " WHERE `name` LIKE ? LIMIT ? OFFSET ?";
    /**
     * Fill rubik. SQL query.
     */
    private static final String FILL_RUBIK =
            "SELECT `model`, `price`, `weight`, `info`, `primary_plastic`, "
                    + "`size`, `plastic_color`, `name_manufacturer`, "
                    + "`name`, `date_added`, `blocked` FROM `rubiks_cube` "
                    + JOIN_RUBIK_WITH_TABLES + "WHERE `rubiks_cube`.`id` = ?";
    /**
     * Read all manufacturer's. SQL query.
     */
    private static final String READ_ALL_MANUFACTURERS =
            "SELECT `name_manufacturer` FROM `manufacturer`";
    /**
     * Read all forms. SQL query.
     */
    private static final String READ_ALL_FORMS = "SELECT `name` FROM `form`";
    /**
     * Read all colors. SQL query.
     */
    private static final String READ_ALL_COLORS =
            "SELECT `plastic_color` FROM `plastic_color`";
    /**
     * Update state of rubik. SQL query.
     */
    private static final String UPDATE_STATE =
            "UPDATE rubiks_cube SET blocked = true WHERE id = ?";
    /**
     * Find all rubik's by manufacturer. SQL query.
     */
    private static final String FIND_ALL_RUBIKS_BY_MANUFACTURER = FIND_RUBIK_BY
            + " WHERE `name_manufacturer` LIKE ? LIMIT ? OFFSET ?";
    /**
     * Find count of rubik's by manufacturer. SQL query.
     */
    private static final String COUNT_RUBIK_BY_MANUFACTURER =
            COUNT_RUBIK_BY + " WHERE `name_manufacturer` LIKE ?";

    /**
     * Find count of rubik's by price in range SQL query.
     */
    private static final String COUNT_RUBIK_BY_PRICE =
            COUNT_RUBIK_BY + " WHERE `price` BETWEEN ? AND ?";
    /**
     * Find count of rubik's by form. SQL query.
     */
    private static final String COUNT_RUBIK_BY_FORM = COUNT_RUBIK_BY
            + " WHERE `name` LIKE ?";
    /**
     * Find count of rubik's by form. SQL query.
     */
    private static final String COUNT_RUBIK_BY_MODEL = COUNT_RUBIK_BY
            + " WHERE `model` LIKE ?";
    /**
     * Find all rubik's by blocked = false(unblocked). SQL query.
     */
    private static final String FIND_ALL_UNBLOCKED_RUBIKS = FIND_RUBIK_BY
            + " WHERE `blocked` = false LIMIT ? OFFSET ?";
    /**
     * Find count of rubik's by blocked = false(unblocked). SQL query.
     */
    private static final String COUNT_RUBIK_BY_UNBLOCKED = COUNT_RUBIK_BY
            + " WHERE blocked = false";
    private static final String FIND_CUBE_BY_MODEL = FIND_RUBIK_BY + "WHERE model = ?";

    /**
     * Constructor.
     *
     * @param managerNew connection manager.
     */
    RubikDaoImpl(
            final AbstractConnectionManager managerNew) {
        super(managerNew);
    }

    /**
     * Find count of rubik's by unblocked.
     *
     * @return count of rubik's.
     * @throws PersistenceException persistent exception.
     */
    @Override
    public int findCountByUnblocked() throws PersistenceException {
        try (PreparedStatement statement =
                     getConnection()
                             .prepareStatement(COUNT_RUBIK_BY_UNBLOCKED);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException eNew) {
            throw new PersistenceException(eNew);
        }
        return 0;
    }

    /**
     * Find rubik's by unblocked in range.
     *
     * @param limit  limit.
     * @param offset offset.
     * @return list of rubik's.
     * @throws PersistenceException persistent exception.
     */
    @Override
    public List<RubiksCube> findRubiksByUnblocked(final int limit,
                                                  final int offset)
            throws PersistenceException {
        try (PreparedStatement statement =
                     getConnection()
                             .prepareStatement(FIND_ALL_UNBLOCKED_RUBIKS)) {
            List<RubiksCube> cubes = new LinkedList<>();
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cubes.add(createRubikFromResultSet(resultSet));
                }
                return cubes;
            }
        } catch (SQLException eNew) {
            throw new PersistenceException(eNew);
        }
    }

    /**
     * Find count of rubik's by model.
     *
     * @param model model.
     * @return count of rubik's.
     * @throws PersistenceException persistent exception.
     */
    @Override
    public int findCountByModel(final String model) throws
            PersistenceException {
        return findCountBy(model, COUNT_RUBIK_BY_MODEL);
    }

    @Override
    public RubiksCube findCubeByModel(String model) throws PersistenceException {
        try (PreparedStatement statement = getConnection().prepareStatement(FIND_CUBE_BY_MODEL)) {
            statement.setString(1, model);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createRubikFromResultSet(resultSet);
                }
            }
        } catch (SQLException eNew) {
            throw new PersistenceException();
        }
        return null;
    }

    @Override
    public int findCountBySize(final String size) throws PersistenceException {
        return findCountBy(size, COUNT_RUBIK_BY_SIZE);
    }

    /**
     * Find count of rubik's by...
     *
     * @param parameter parameter.
     * @param query     query.
     * @return count of rubik's.
     * @throws PersistenceException persistent exception.
     */
    private int findCountBy(final String parameter, final String query)
            throws PersistenceException {
        try (PreparedStatement statement =
                     getConnection().prepareStatement(query)) {
            statement.setString(1, parameter + '%');
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException eNew) {
            throw new PersistenceException(eNew);
        }
        return 0;
    }

    /**
     * Find count of rubik's by form.
     *
     * @param form form.
     * @return count of rubik's.
     * @throws PersistenceException persistent exception.
     */
    @Override
    public int findCountByForm(final String form) throws
            PersistenceException {
        return findCountBy(form, COUNT_RUBIK_BY_FORM);
    }

    /**
     * Find count rubik's by price in range.
     *
     * @param min min price.
     * @param max max price.
     * @return count rubik's by price range.
     * @throws PersistenceException persistent exception.
     */
    @Override
    public int findCountByPrice(final double min, final double max)
            throws PersistenceException {
        try (PreparedStatement statement =
                     getConnection()
                             .prepareStatement(COUNT_RUBIK_BY_PRICE)) {
            statement.setDouble(1, min);
            statement.setDouble(2, max);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException eNew) {
            throw new PersistenceException(eNew);
        }
        return 0;
    }

    /**
     * Find all rubik's in range.
     *
     * @param offset offset.
     * @param limit  limit.
     * @return list of rubik's.
     * @throws PersistenceException dao exception.
     */
    @Override
    public List<RubiksCube> findAll(final int offset, final int limit)
            throws PersistenceException {
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
            throw new PersistenceException(newE.getMessage(), newE);
        }
    }

    /**
     * Find all rubik's by manufacturer.
     *
     * @param manufacturer manufacturer.
     * @param limit        limit.
     * @param offset       offset.
     * @return list of rubik's.
     * @throws PersistenceException persistent exception.
     */
    @Override
    public List<RubiksCube> findRubiksByManufacturer(
            final String manufacturer,
            final int limit,
            final int offset)
            throws PersistenceException {
        return getCubes(manufacturer, offset, limit,
                FIND_ALL_RUBIKS_BY_MANUFACTURER);
    }

    /**
     * Find rubik by id.
     *
     * @param id id of object.
     * @return rubik.
     * @throws PersistenceException dao exception.
     */
    @Override
    public RubiksCube findEntityById(final long id)
            throws PersistenceException {
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
            throw new PersistenceException(
                    "SQLException while finding by id",
                    e);
        }
        return rubiksCube;
    }

    /**
     * Update rubik.
     *
     * @param entityNew object.
     * @throws PersistenceException dao exception.
     */
    @Override
    public void update(final RubiksCube entityNew)
            throws PersistenceException {
        try (PreparedStatement statement = getConnection()
                .prepareStatement(UPDATE_RUBIK_BY_ID)) {
            execute(statement, entityNew);
            statement.setLong(11, entityNew.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("SQLException while updating", e);
        }
    }

    /**
     * Create rubik.
     *
     * @param entityNew object.
     * @return id of cube in table.
     * @throws PersistenceException dao exception.
     */
    @Override
    public int create(final RubiksCube entityNew)
            throws PersistenceException {
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
            throw new PersistenceException(
                    "SQLException while inserting rubik", e);
        }
        return 0;
    }

    /**
     * Find rubik's by size in range.
     *
     * @param size   size of cube.
     * @param offset offset.
     * @param limit  limit.
     * @return list of cubes.
     * @throws PersistenceException dao exception.
     */
    @Override
    public List<RubiksCube> findRubiksBySize(final String size,
                                             final int offset, final int limit)
            throws PersistenceException {
        return getCubes(size, offset, limit, FIND_ALL_RUBIKS_BY_SIZE);
    }

    /**
     * Find rubik's by model in range.
     *
     * @param model  model.
     * @param limit  limit.
     * @param offset offset.
     * @return list of rubik's.
     * @throws PersistenceException dao exception.
     */
    @Override
    public List<RubiksCube> findRubikByModel(final String model,
                                             final int limit, final int offset)
            throws PersistenceException {
        return getCubes(model, offset, limit, FIND_RUBIK_BY_MODEL);
    }

    /**
     * Read info about rubik.
     *
     * @param rubiksCubeNew cube.
     * @throws PersistenceException dao exception.
     */
    @Override
    public void read(final RubiksCube rubiksCubeNew)
            throws PersistenceException {
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(FILL_RUBIK)) {
            preparedStatement.setLong(1, rubiksCubeNew.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    buildCube(resultSet, rubiksCubeNew);
                }
            }
        } catch (SQLException eNew) {
            throw new PersistenceException(eNew);
        }
    }

    /**
     * Find rubik's by range of price in range.
     *
     * @param minPrice minimum price.
     * @param maxPrice maximum price.
     * @param offset   offset.
     * @param limit    limit.
     * @return list of rubik's.
     * @throws PersistenceException dao exception.
     */
    @Override
    public List<RubiksCube> findRubiksByRangePrice(final double minPrice,
                                                   final double maxPrice,
                                                   final int offset,
                                                   final int limit)
            throws PersistenceException {
        List<RubiksCube> rubiksCubes = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_ALL_RUBIKS_BY_PRICE)) {
            int i = 0;
            preparedStatement.setDouble(++i, minPrice);
            preparedStatement.setDouble(++i, maxPrice);
            preparedStatement.setInt(++i, limit);
            preparedStatement.setInt(++i, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    rubiksCubes.add(createRubikFromResultSet(resultSet));
                }
            }
            return rubiksCubes;
        } catch (SQLException newE) {
            throw new PersistenceException(newE.getMessage(), newE);
        }
    }

    /**
     * Find rubik's by form in range.
     *
     * @param form   form of cube.
     * @param offset offset.
     * @param limit  limit.
     * @return list of rubik's.
     * @throws PersistenceException dao exception.
     */
    @Override
    public List<RubiksCube> findRubiksByForm(final String form,
                                             final int offset,
                                             final int limit)
            throws PersistenceException {
        return getCubes(form, offset, limit, FIND_ALL_RUBIKS_BY_FORM);
    }

    /**
     * Getter.
     *
     * @param parameter       parameter.
     * @param offset          offset.
     * @param limit           limit.
     * @param findAllRubiksBy sql query.
     * @return list of rubik's.
     * @throws PersistenceException dao exception.
     */
    private List<RubiksCube> getCubes(final String parameter,
                                      final int offset,
                                      final int limit,
                                      final String findAllRubiksBy)
            throws PersistenceException {
        List<RubiksCube> rubiksCubes = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(findAllRubiksBy)) {
            int i = 0;
            preparedStatement.setString(++i, parameter + '%');
            preparedStatement.setInt(++i, limit);
            preparedStatement.setInt(++i, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    rubiksCubes.add(createRubikFromResultSet(resultSet));
                }
            }
            return rubiksCubes;
        } catch (SQLException newE) {
            throw new PersistenceException(newE.getMessage(), newE);
        }
    }

    /**
     * Read all manufacturers.
     *
     * @return list of manufacturers.
     * @throws PersistenceException dao exception.
     */
    @Override
    public List<String> readAllManufacturer() throws PersistenceException {
        List<String> manufacturers = new ArrayList<>();
        try (PreparedStatement statement =
                     getConnection()
                             .prepareStatement(READ_ALL_MANUFACTURERS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                manufacturers.add(resultSet.getString("name_manufacturer"));
            }
            return manufacturers;
        } catch (SQLException eNew) {
            throw new PersistenceException(eNew);
        }
    }

    /**
     * Read all forms.
     *
     * @return list of forms.
     * @throws PersistenceException dao exception.
     */
    @Override
    public List<String> readAllForm() throws PersistenceException {
        List<String> forms = new ArrayList<>();
        try (PreparedStatement statement =
                     getConnection().prepareStatement(READ_ALL_FORMS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                forms.add(resultSet.getString("name"));
            }
            return forms;
        } catch (SQLException eNew) {
            throw new PersistenceException(eNew);
        }
    }

    /**
     * Read all plastic colors.
     *
     * @return list of plastic colors.
     * @throws PersistenceException dao exception.
     */
    @Override
    public List<String> readAllPlasticColor() throws PersistenceException {
        List<String> colors = new ArrayList<>();
        try (PreparedStatement statement =
                     getConnection().prepareStatement(READ_ALL_COLORS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                colors.add(resultSet.getString("plastic_color"));
            }
            return colors;
        } catch (SQLException eNew) {
            throw new PersistenceException(eNew);
        }
    }

    /**
     * Find coutn rubik's by manufacturer.
     *
     * @param manufacturer manufacturer.
     * @return count rubik's by manufacturer.
     * @throws PersistenceException persistent exception.
     */
    @Override
    public int findCountByManufacturer(final String manufacturer)
            throws PersistenceException {
        return findCountBy(manufacturer, COUNT_RUBIK_BY_MANUFACTURER);
    }

    /**
     * Update state of rubik. Ban.
     *
     * @param rubiksCubeNew cube.
     * @throws PersistenceException dao exception.
     */
    @Override
    public void updateState(final RubiksCube rubiksCubeNew)
            throws PersistenceException {
        try (PreparedStatement statement =
                     getConnection().prepareStatement(UPDATE_STATE)) {
            statement.setLong(1, rubiksCubeNew.getId());
            statement.executeUpdate();
        } catch (SQLException eNew) {
            throw new PersistenceException(eNew);
        }
    }

    /**
     * Find count of rubik's.
     *
     * @return count of rubik's.
     * @throws PersistenceException dao exception.
     */
    @Override
    public int findElementCount() throws PersistenceException {
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

    /**
     * Execute statement.
     *
     * @param statement statement.
     * @param entityNew rubik.
     * @throws SQLException sql exception.
     */
    private void execute(final PreparedStatement statement,
                         final RubiksCube entityNew)
            throws SQLException {
        int i = 0;
        statement.setString(++i, entityNew.getModel());
        statement.setDouble(++i, entityNew.getPrice());
        statement.setDouble(++i, entityNew.getWeight());
        statement.setString(++i, entityNew.getInfo());
        statement.setBoolean(++i, entityNew.isPrimaryPlastic());
        statement.setString(++i, entityNew.getSize());
        statement.setString(++i, entityNew.getPlasticColor());
        statement.setString(++i, entityNew.getManufacturer());
        statement.setString(++i, entityNew.getForm());
        statement.setDate(++i, new Date(entityNew.getDate().getTime()));
    }

    /**
     * Create rubik from result set.
     *
     * @param resultSet result set.
     * @return rubik.
     * @throws SQLException sql exception.
     */
    private RubiksCube createRubikFromResultSet(final ResultSet resultSet)
            throws SQLException {
        RubiksCube cube = new RubiksCube(resultSet.getLong("id"));
        buildCube(resultSet, cube);
        return cube;
    }

    /**
     * Build rubik.
     *
     * @param resultSet result set.
     * @param cubeNew   rubik.
     * @throws SQLException sql exception.
     */
    private void buildCube(final ResultSet resultSet,
                           final RubiksCube cubeNew)
            throws SQLException {
        cubeNew.setModel(resultSet.getString("model"));
        cubeNew.setPrice(resultSet.getDouble("price"));
        cubeNew.setWeight(resultSet.getDouble("weight"));
        cubeNew.setInfo(resultSet.getString("info"));
        cubeNew.setPrimaryPlastic(resultSet.getBoolean(
                "primary_plastic"));
        cubeNew.setSize(resultSet.getString("size"));
        cubeNew.setPlasticColor(resultSet.getString(
                "plastic_color"));
        cubeNew.setManufacturer(resultSet.getString(
                "name_manufacturer"));
        cubeNew.setForm(resultSet.getString("name"));
        cubeNew.setDate(resultSet.getDate("date_added"));
        cubeNew.setBlocked(resultSet.getBoolean("blocked"));
    }
}
