package by.training.catalog.service.impl;

import by.training.catalog.bean.Role;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.User;
import by.training.catalog.dao.AbstractConnectionManager;
import by.training.catalog.dao.PersistenceException;
import by.training.catalog.dao.RubikDao;
import by.training.catalog.dao.UserDao;
import by.training.catalog.service.AbstractService;
import by.training.catalog.service.PasswordEncoder;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import by.training.catalog.service.factory.UserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
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
     * Password encoder.
     */
    private final PasswordEncoder passwordEncoder = new ArgonEncoder();

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
     * @throws ServiceException if {@link PersistenceException} has occurred
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
                if (entityNew != null
                        && userDao.findEntityById(entityNew.getId()) != null
                        && entityNew.getPassword() != null) {
                    entityNew.setPassword(
                            passwordEncoder
                                    .encodePassword(entityNew.getPassword()));
                    userDao.update(entityNew);
                    entityNew.setPassword(null);
                    connectionManager.commit();
                } else {
                    throw new ServiceException("user not initialized");
                }
            } catch (PersistenceException eNew) {
                connectionManager.rollback();
                throw new ServiceException(eNew);
            }
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Find count of rubik's in bookmarks.
     *
     * @param id id of user.
     * @return count of rubik's.
     * @throws ServiceException service exception.
     */
    @Override
    public int findCountRubiks(final long id) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao = getDaoFactory().createUserDao(connectionManager);
            User user = userDao.findEntityById(id);
            if (user != null) {
                return userDao.findCountOfRubiks(user);
            } else {
                throw new ServiceException("user is not logged in");
            }
        } catch (PersistenceException eNew) {
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
     * @throws ServiceException if {@link PersistenceException} has occurred
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
                    UserFactory parser = new UserFactory();
                    User entityNew =
                            parser.createUser(username, email, phone, password);
                    if (entityNew != null) {
                        entityNew.setPassword(
                                passwordEncoder.encodePassword(
                                        entityNew.getPassword()));
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
                }
            } catch (PersistenceException eNew) {
                connectionManager.rollback();
                throw new ServiceException(eNew);
            }
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
        return flag;
    }

    /**
     * Find user by email.
     *
     * @param email email.
     * @return user.
     * @throws ServiceException if {@link PersistenceException} has occurred
     *                          when working with database.
     */
    @Override
    public User findUserByEmail(final String email) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createUserDao(connectionManager);
            return userDao.findUserByEmail(email);
        } catch (PersistenceException eNew) {
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
     * @throws ServiceException if {@link PersistenceException} has occurred
     *                          when working with database.
     */
    @Override
    public List<User> findUsersByUsername(final String username,
                                          final int limit, final int offset)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            if (username != null) {
                UserDao userDao =
                        getDaoFactory().createUserDao(connectionManager);
                return userDao.findUsersByUsername(username, limit, offset);
            } else {
                return new ArrayList<>();
            }
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Find user by username.
     *
     * @param username username.
     * @return user.
     * @throws ServiceException if {@link PersistenceException} has occurred
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
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Authorize user in app.
     *
     * @param username username.
     * @param password password.
     * @return user.
     * @throws ServiceException if {@link PersistenceException} has occurred
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
            if (user != null && password != null && passwordEncoder
                    .verify(user.getPassword(),
                            password)) {
                user.setPassword(null);
                return user;
            }
        } catch (PersistenceException eNew) {
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
     * @throws ServiceException if {@link PersistenceException} has occurred
     *                          when working with database.
     */
    @Override
    public List<User> findUsersByRole(final Role role, final int limit,
                                      final int offset)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            if (role != null) {
                UserDao userDao =
                        getDaoFactory().createUserDao(connectionManager);
                return userDao.findUsersByRole(role, limit, offset);
            } else {
                return new ArrayList<>();
            }
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Find user by id.
     *
     * @param id id of user.
     * @return user.
     * @throws ServiceException if {@link PersistenceException} has occurred
     *                          when working with database.
     */
    @Override
    public User findById(final long id) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createUserDao(connectionManager);
            return userDao.findEntityById(id);
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Update state of user. Ban.
     *
     * @param id id of user.
     * @throws ServiceException if {@link PersistenceException} has occurred
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
                if (userNew != null) {
                    userDao.updateState(userNew);
                    connectionManager.commit();
                    LOGGER.debug("<-----User id = {} state = {} commit",
                            userNew.getId(),
                            userNew.isBlocked());
                }
            } catch (PersistenceException eNew) {
                connectionManager.rollback();
                throw new ServiceException(eNew);
            }
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Find liked cube by user in range.
     *
     * @param userNew user.
     * @param limit   limit.
     * @param offset  offset.
     * @throws ServiceException if {@link PersistenceException} has occurred
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
            if (userNew != null
                    && userDao.findEntityById(userNew.getId()) != null) {
                LOGGER.debug("Service cubes {}",
                        userDao.findLikedCubesByUser(userNew, limit, offset));
                userNew.setCubes(userDao.findLikedCubesByUser(userNew, limit,
                        offset));
                RubikDao rubikDao =
                        getDaoFactory().createRubikDao(connectionManager);
                for (RubiksCube rubiksCube : userNew.getCubes()) {
                    rubikDao.read(rubiksCube);
                }
            } else {
                throw new ServiceException("User is not logged in");
            }
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Find all rubik's in range.
     *
     * @param offset offset.
     * @param limit  limit.
     * @return list of users.
     * @throws ServiceException if {@link PersistenceException} has occurred
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
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Add cube to bookmarks.
     *
     * @param userNew user.
     * @param cubeId  id of cube.
     * @return true or false.
     * @throws ServiceException if {@link PersistenceException} has occurred
     *                          when working with database.
     */
    @Override
    public boolean addCubeToBookmarks(final User userNew, final long cubeId)
            throws ServiceException {
        boolean flag;
        if (userNew == null) {
            throw new ServiceException("user is null");
        }
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
            } catch (PersistenceException eNew) {
                connectionManager.rollback();
                throw new ServiceException(eNew);
            }
        } catch (PersistenceException eNew) {
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
     * @throws ServiceException if {@link PersistenceException} has occurred
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
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Remove rubik from bookmarks.
     *
     * @param userNew user.
     * @param id      id of cube.
     * @throws ServiceException if {@link PersistenceException} has occurred
     *                          when working with database.
     */
    @Override
    public void removeFromBookmarks(final User userNew, final long id)
            throws ServiceException {
        if (userNew == null) {
            throw new ServiceException("user is null");
        }
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                connectionManager.disableAutoCommit();
                UserDao userDao =
                        getDaoFactory().createUserDao(connectionManager);
                userDao.removeCubeFromBookmarks(userNew, id);
                connectionManager.commit();
            } catch (PersistenceException eNew) {
                connectionManager.rollback();
                throw new ServiceException(eNew);
            }
        } catch (PersistenceException eNew) {
            throw new ServiceException(eNew);
        }
    }

    /**
     * Find count of users.
     *
     * @return count of users.
     * @throws ServiceException if {@link PersistenceException} has occurred
     *                          when working with database.
     */
    @Override
    public int findElementCount() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao accountDao =
                    getDaoFactory().createUserDao(connectionManager);
            return accountDao.findElementCount();
        } catch (PersistenceException e) {
            throw new ServiceException(e);
        }
    }
}
