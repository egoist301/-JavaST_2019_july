package by.training.catalog.dao.impl;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.StoreImage;
import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.PersistenceException;
import by.training.catalog.dao.StoreImageDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * Store image dao implementation.
 */
public class StoreImageDaoImpl implements
        StoreImageDao {
    /**
     * Insert store image. SQL query.
     */
    private static final String INSERT_STORE_IMAGE = "INSERT INTO "
            + "`store_image`(`cube_id`, `image_path`) VALUES (?, ?)";
    /**
     * Find all images by rubik. SQL query.
     */
    private static final String FIND_ALL_IMAGES_BY_RUBIK = "SELECT "
            + "`image_path` FROM `store_image` WHERE `cube_id` = ?";
    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Default constructor.
     *
     * @param managerNew connection manager.
     */
    StoreImageDaoImpl(final AbstractConnectionManager managerNew) {
        connection = managerNew.getConnection();
    }

    /**
     * Find images by rubik.
     *
     * @param cubeNew cube.
     * @return list of paths.
     * @throws PersistenceException dao exception.
     */
    @Override
    public List<String> findImagesByRubik(final RubiksCube cubeNew)
            throws PersistenceException {
        List<String> list = new LinkedList<>();
        try (PreparedStatement statement = connection
                .prepareStatement(FIND_ALL_IMAGES_BY_RUBIK)) {
            statement.setLong(1, cubeNew.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(resultSet.getString("image_path"));
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(
                    "SQLException while finding all images", e);
        }
        return list;
    }

    /**
     * Create.
     *
     * @param entityNew object.
     * @return id of store image.
     * @throws PersistenceException dao exception.
     */
    @Override
    public int create(final StoreImage entityNew) throws PersistenceException {
        if (entityNew == null) {
            return 0;
        }
        try (PreparedStatement statement = connection
                .prepareStatement(INSERT_STORE_IMAGE, RETURN_GENERATED_KEYS)) {
            statement.setLong(1, entityNew.getRubiksCube().getId());
            statement.setString(2, entityNew.getImgPath());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(
                    "SQLException while inserting store image", e);
        }
        return 0;
    }
}
