package by.training.catalog.service.impl;

import by.training.catalog.bean.Role;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.User;
import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.ConnectionManagerFactory;
import by.training.catalog.dao.DaoFactory;
import by.training.catalog.dao.PersistentException;
import by.training.catalog.dao.RubikDao;
import by.training.catalog.dao.UserDao;
import by.training.catalog.service.AbstractService;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import by.training.catalog.service.parser.UserParser;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * User service implementation.
 */
public class UserServiceImpl extends AbstractService implements UserService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Argon2. Algorithm encoding.
     */
    private final Argon2 argon2 =
            Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    /**
     * Constructor with parameters.
     *
     * @param daoFactoryNew               factory of dao.
     * @param connectionManagerFactoryNew connection manager factory.
     */
    public UserServiceImpl(final DaoFactory daoFactoryNew,
                           final ConnectionManagerFactory
                                   connectionManagerFactoryNew) {
        super(daoFactoryNew, connectionManagerFactoryNew);
    }

    /**
     * Default constructor.
     */
    public UserServiceImpl() {
        super();
    }

    /**
     * Update info about user.
     *
     * @param entityNew user.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    @Override
    public void update(final User entityNew) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                connectionManager.disableAutoCommit();
                UserDao userDao =
                        getDaoFactory().createUserDao(connectionManager);
                entityNew.setPassword(
                        argonTwoHashAlgorithm(entityNew.getPassword()));
                userDao.update(entityNew);
                entityNew.setPassword(null);
                connectionManager.commit();
            } catch (PersistentException eNew) {
                connectionManager.rollback();
                throw new ServiceException(eNew);
            }
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public int findCountRubiks(final long id) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao = getDaoFactory().createUserDao(connectionManager);
            User user = userDao.findEntityById(id);
            if (user != null) {
                return userDao.findCountOfRubiks(user);
            } else {
                return 0;
            }
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Create user.
     *
     * @param username username.
     * @param email    email.
     * @param phone    phone.
     * @param password password.
     * @return true or false.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    @Override
    public boolean create(final String username, final String email,
                          final String phone, final String password)
            throws ServiceException {
        boolean flag;
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                connectionManager.disableAutoCommit();
                if (findUserByUsername(username) == null
                        && findUserByEmail(email) == null) {
                    UserDao userDao =
                            getDaoFactory().createUserDao(connectionManager);
                    UserParser parser = new UserParser();
                    User entityNew =
                            parser.getUser(username, email, phone, password);
                    if (entityNew != null) {
                        entityNew.setPassword(
                                argonTwoHashAlgorithm(entityNew.getPassword()));
                        entityNew.setId(userDao.create(entityNew));
                        entityNew.setPassword(null);
                        connectionManager.commit();
                        flag = true;
                    } else {
                        connectionManager.rollback();
                        flag = false;
                    }
                } else {
                    flag = false;
                    connectionManager.rollback();
                }
            } catch (PersistentException eNew) {
                connectionManager.rollback();
                throw new ServiceException(eNew);
            }
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
        return flag;
    }

    /**
     * Find user by email.
     *
     * @param email email.
     * @return user.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    @Override
    public User findUserByEmail(final String email) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createUserDao(connectionManager);
            return userDao.findUserByEmail(email);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Find users by username.
     *
     * @param username username.
     * @param limit    limit.
     * @param offset   offset.
     * @return list of user in range.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    @Override
    public List<User> findUsersByUsername(final String username,
                                          final int limit, final int offset)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createUserDao(connectionManager);
            return userDao.findUsersByUsername(username, limit, offset);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Find user by username.
     *
     * @param username username.
     * @return user.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    @Override
    public User findUserByUsername(final String username)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createUserDao(connectionManager);
            return userDao.findUserByUsername(username);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Authorize user in app.
     *
     * @param username username.
     * @param password password.
     * @return user.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    @Override
    public User authorize(final String username,
                          final String password)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createUserDao(connectionManager);
            User user = userDao.findUserByUsername(username);
            if (user != null && argon2.verify(user.getPassword(), password)) {
                user.setPassword(null);
                return user;
            }
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
        return null;
    }

    /**
     * Find users by role.
     *
     * @param role   role.
     * @param limit  limit.
     * @param offset offset.
     * @return list of users in range.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    @Override
    public List<User> findUsersByRole(final Role role, final int limit,
                                      final int offset)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createUserDao(connectionManager);
            return userDao.findUsersByRole(role, limit, offset);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Find user by id.
     *
     * @param id id of user.
     * @return user.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    @Override
    public User findById(final long id) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createUserDao(connectionManager);
            return userDao.findEntityById(id);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Update state of user. Ban.
     *
     * @param id id of user.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    @Override
    public void updateState(final long id) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                connectionManager.disableAutoCommit();
                UserDao userDao =
                        getDaoFactory().createUserDao(connectionManager);
                User userNew = userDao.findEntityById(id);
                userDao.updateState(userNew);
                connectionManager.commit();
                LOGGER.debug("<-----User id = {} state = {} commit",
                        userNew.getId(),
                        userNew.isBlocked());
            } catch (PersistentException eNew) {
                connectionManager.rollback();
                throw new ServiceException(eNew);
            }
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Find liked cube by user in range.
     *
     * @param userNew user.
     * @param limit   limit.
     * @param offset  offset.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    @Override
    public void findLikedCubes(final User userNew, final int limit,
                               final int offset)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createUserDao(connectionManager);
            LOGGER.debug("Service cubes {}",
                    userDao.findLikedCubesByUser(userNew, limit, offset));
            userNew.setCubes(userDao.findLikedCubesByUser(userNew, limit,
                    offset));
            RubikDao rubikDao =
                    getDaoFactory().createRubikDao(connectionManager);
            for (RubiksCube rubiksCube : userNew.getCubes()) {
                rubikDao.read(rubiksCube);
            }
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Find all rubik's in range.
     *
     * @param offset offset.
     * @param limit  limit.
     * @return list of users.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    @Override
    public List<User> findAll(final int offset, final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createUserDao(connectionManager);
            return userDao.findAll(offset, limit);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Add cube to bookmarks.
     *
     * @param userNew user.
     * @param cubeId  id of cube.
     * @return true or false.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    @Override
    public boolean addCubeToBookmarks(final User userNew, final long cubeId)
            throws ServiceException {
        boolean flag;
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                connectionManager.disableAutoCommit();
                UserDao userDao =
                        getDaoFactory().createUserDao(connectionManager);
                RubiksCube cube1 = findCubeFromBookmarks(userNew, cubeId);
                if (cube1 == null) {
                    userDao.addCubeToBookmarks(userNew, cubeId);
                    connectionManager.commit();
                    flag = true;
                } else {
                    connectionManager.rollback();
                    flag = false;
                }
            } catch (PersistentException eNew) {
                connectionManager.rollback();
                throw new ServiceException(eNew);
            }
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
        return flag;
    }

    /**
     * Find cube from bookmarks.
     *
     * @param userNew user.
     * @param cubeId  id of cube.
     * @return rubik.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    @Override
    public RubiksCube findCubeFromBookmarks(final User userNew,
                                            final long cubeId)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createUserDao(connectionManager);
            return userDao.findCubeFromBookmarksById(userNew, cubeId);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Remove rubik from bookmarks.
     *
     * @param userNew user.
     * @param id      id of cube.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    @Override
    public void removeFromBookmarks(final User userNew, final long id)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                connectionManager.disableAutoCommit();
                UserDao userDao =
                        getDaoFactory().createUserDao(connectionManager);
                userDao.removeCubeFromBookmarks(userNew, id);
                connectionManager.commit();
            } catch (PersistentException eNew) {
                connectionManager.rollback();
                throw new ServiceException(eNew);
            }
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Find count of users.
     *
     * @return count of users.
     * @throws ServiceException if {@link PersistentException} has occurred
     *                          when working with database.
     */
    @Override
    public int findElementCount() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao accountDao =
                    getDaoFactory().createUserDao(connectionManager);
            return accountDao.findElementCount();
        } catch (PersistentException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Encoding.
     *
     * @param newPassword password.
     * @return hash.
     */
    private String argonTwoHashAlgorithm(final String newPassword) {
        final int iNew = 4;
        final int iNew1 = 1024;
        return argon2.hash(iNew, iNew1 * iNew1, iNew, newPassword);
    }
}
