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
            "UPDATE `rubiks_cube`" + JOIN_RUBIK_WITH_TABLES + " SET `model` = "
                    + "?, `price` = ?, `weight` ="
                    + " ?, `info` = ?, `primary_plastic` = ?, `size` = ?,"
                    + " `plastic_color` = ?, `name_manufacturer` = ?, "
                    + "`name` = ?, `date_added` = ? WHERE "
                    + "`rubiks_cube`.`id` = ?";
    /**
     * Insert rubik. SQL query.
     */
    private static final String INSERT_RUBIK =
            "INSERT INTO `rubiks_cube` (`model`, "
                    + "`price`, `weight`, `info`, `primary_plastic`, `size`, "
                    + "`plastic_color_id`, `manufacturer_id`, `form_id`, "
                    + "`date_added`, `blocked`) VALUES (?, ?, ?, ?, ?, ?, "
                    + "?, ?,  ?, ?, false)";
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
     * Constructor.
     *
     * @param managerNew connection manager.
     */
    RubikDaoImpl(final AbstractConnectionManager managerNew) {
        super(managerNew);
    }

    /**
     * Find all rubik's in range.
     *
     * @param offset offset.
     * @param limit  limit.
     * @return list of rubik's.
     * @throws PersistentException dao exception.
     */
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

    /**
     * Find rubik by id.
     *
     * @param id id of object.
     * @return rubik.
     * @throws PersistentException dao exception.
     */
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

    /**
     * Update rubik.
     *
     * @param entityNew object.
     * @throws PersistentException dao exception.
     */
    @Override
    public void update(final RubiksCube entityNew)
            throws PersistentException {
        try (PreparedStatement statement = getConnection()
                .prepareStatement(UPDATE_RUBIK_BY_ID)) {
            execute(statement, entityNew);
            statement.setLong(11, entityNew.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException("SQLException while updating", e);
        }
    }

    /**
     * Create rubik.
     *
     * @param entityNew object.
     * @return id of cube in table.
     * @throws PersistentException dao exception.
     */
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

    /**
     * Find rubik's by size in range.
     *
     * @param size   size of cube.
     * @param offset offset.
     * @param limit  limit.
     * @return list of cubes.
     * @throws PersistentException dao exception.
     */
    @Override
    public List<RubiksCube> findRubiksBySize(final String size,
                                             final int offset, final int limit)
            throws PersistentException {
        return getCubes(size, offset, limit, FIND_ALL_RUBIKS_BY_SIZE);
    }

    /**
     * Find rubik's by model in range.
     *
     * @param model  model.
     * @param limit  limit.
     * @param offset offset.
     * @return list of rubik's.
     * @throws PersistentException dao exception.
     */
    @Override
    public List<RubiksCube> findRubikByModel(final String model,
                                             final int limit, int offset)
            throws PersistentException {
        List<RubiksCube> rubiksCubes = new ArrayList<>();
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_RUBIK_BY_MODEL)) {
            statement.setString(1, model + '%');
            statement.setInt(2, limit);
            statement.setInt(3, offset);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    rubiksCubes.add(createRubikFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new PersistentException("SQLException while finding by "
                    + "model", e);
        }
        return rubiksCubes;
    }

    /**
     * Read info about rubik.
     *
     * @param rubiksCubeNew cube.
     * @throws PersistentException dao exception.
     */
    @Override
    public void read(final RubiksCube rubiksCubeNew)
            throws PersistentException {
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(FILL_RUBIK)) {
            preparedStatement.setLong(1, rubiksCubeNew.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    buildCube(resultSet, rubiksCubeNew);
                }
            }
        } catch (SQLException eNew) {
            throw new PersistentException(eNew);
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
     * @throws PersistentException dao exception.
     */
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

    /**
     * Find rubik's by form in range.
     *
     * @param form   form of cube.
     * @param offset offset.
     * @param limit  limit.
     * @return list of rubik's.
     * @throws PersistentException dao exception.
     */
    @Override
    public List<RubiksCube> findRubiksByForm(final String form,
                                             final int offset,
                                             final int limit)
            throws PersistentException {
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
     * @throws PersistentException dao exception.
     */
    private List<RubiksCube> getCubes(final String parameter, final int offset,
                                      final int limit,
                                      final String findAllRubiksBy)
            throws PersistentException {
        List<RubiksCube> rubiksCubes = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(findAllRubiksBy)) {
            preparedStatement.setString(1, parameter + '%');
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

    /**
     * Read all manufacturers.
     *
     * @return list of manufacturers.
     * @throws PersistentException dao exception.
     */
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

    /**
     * Read all forms.
     *
     * @return list of forms.
     * @throws PersistentException dao exception.
     */
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

    /**
     * Read all plastic colors.
     *
     * @return list of plastic colors.
     * @throws PersistentException dao exception.
     */
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

    /**
     * Update state of rubik. Ban.
     *
     * @param rubiksCubeNew cube.
     * @throws PersistentException dao exception.
     */
    @Override
    public void updateState(final RubiksCube rubiksCubeNew)
            throws PersistentException {
        try (PreparedStatement statement =
                     getConnection().prepareStatement(UPDATE_STATE)) {
            statement.setLong(1, rubiksCubeNew.getId());
            statement.executeUpdate();
        } catch (SQLException eNew) {
            throw new PersistentException(eNew);
        }
    }

    /**
     * Find count of rubik's.
     *
     * @return count of rubik's.
     * @throws PersistentException dao exception.
     */
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
    private void buildCube(final ResultSet resultSet, final RubiksCube cubeNew)
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
