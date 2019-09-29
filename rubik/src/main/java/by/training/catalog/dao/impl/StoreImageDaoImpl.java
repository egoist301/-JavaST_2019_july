package by.training.catalog.dao.impl;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.StoreImage;
import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.PersistentException;
import by.training.catalog.dao.StoreImageDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class StoreImageDaoImpl extends AbstractDao<StoreImage> implements
        StoreImageDao {
    private static final String INSERT_STORE_IMAGE = "INSERT INTO "
            + "`store_image`(`cube_id`, `image_path`) VALUES (?, ?)";
    private static final String FIND_STORE_IMAGE_BY_ID =
            "SELECT `id`, `cube_id`, `image_path` FROM `store_image` WHERE "
                    + "`id` = ?";
    private static final String UPDATE_STORE_IMAGE_BY_ID = "UPDATE "
            + "`store_image` SET `cube_id` = ?, `image_path` = ? WHERE `id` ="
            + " ?";
    private static final String FIND_STORE_IMAGE_COUNT =
            "SELECT COUNT(`id`) FROM `store_image`";
    private static final String FIND_ALL_STORE_IMAGES =
            "SELECT `id`, `cube_id`, `image_path` FROM `store_image` ORDER BY"
                    + " `id`";
    private static final String FIND_ALL_STORE_IMAGES_LIMIT =
            "SELECT `id`, `cube_id`, `image_path` FROM `store_image` ORDER BY"
                    + " `id` LIMIT ? OFFSET ?";
    private static final String FIND_ALL_IMAGES_BY_RUBIK = "SELECT "
            + "`image_path` FROM `store_image` WHERE `cube_id` = ?";

    StoreImageDaoImpl(
            final AbstractConnectionManager managerNew) {
        super(managerNew);
    }

    @Override
    public List<String> findImagesByRubik(final RubiksCube cubeNew)
            throws PersistentException {
        List<String> list = new LinkedList<>();
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_ALL_IMAGES_BY_RUBIK)) {
            statement.setLong(1, cubeNew.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(resultSet.getString("image_path"));
                }
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding all images", e);
        }
        return list;
    }

    @Override
    public List<StoreImage> findAll() throws PersistentException {
        List<StoreImage> list = new LinkedList<>();
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_ALL_STORE_IMAGES);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                list.add(createStoreImage(resultSet));
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding all store images", e);
        }
        return list;
    }

    @Override
    public List<StoreImage> findAll(final int offset, final int limit)
            throws PersistentException {
        List<StoreImage> list = new LinkedList<>();
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_ALL_STORE_IMAGES_LIMIT)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(createStoreImage(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding all store images", e);
        }
        return list;
    }

    @Override
    public StoreImage findEntityById(final long id) throws PersistentException {
        StoreImage storeImage = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_STORE_IMAGE_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    storeImage = createStoreImage(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new PersistentException("SQLException while finding by id",
                    e);
        }
        return storeImage;
    }

    private StoreImage createStoreImage(final ResultSet resultSetNew)
            throws SQLException {
        long id = resultSetNew.getLong(1);
        long cubeId = resultSetNew.getLong(2);
        String img = resultSetNew.getString(3);
        return new StoreImage(id, new RubiksCube(cubeId), img);
    }

    @Override
    public void update(final StoreImage entityNew) throws PersistentException {
        try (PreparedStatement statement = getConnection()
                .prepareStatement(UPDATE_STORE_IMAGE_BY_ID)) {
            statement.setLong(1, entityNew.getRubiksCube().getId());
            statement.setString(2, entityNew.getImgPath());
            statement.setLong(3, entityNew.getId());
        } catch (SQLException e) {
            throw new PersistentException("SQLException while updating", e);
        }
    }

    @Override
    public int create(final StoreImage entityNew) throws PersistentException {
        if (entityNew == null) {
            return 0;
        }
        try (PreparedStatement statement = getConnection()
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
            throw new PersistentException(
                    "SQLException while inserting store image", e);
        }
        return 0;
    }

    @Override
    public int findElementCount() throws PersistentException {
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_STORE_IMAGE_COUNT)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new PersistentException(
                    "SQLException while finding count",
                    e);
        }
        return 0;
    }
}
