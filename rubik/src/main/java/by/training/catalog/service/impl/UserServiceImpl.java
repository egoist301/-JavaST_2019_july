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

public class UserServiceImpl extends AbstractService implements UserService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Argon2 argon2 =
            Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    public UserServiceImpl(final DaoFactory daoFactoryNew,
                           final ConnectionManagerFactory
                                   connectionManagerFactoryNew) {
        super(daoFactoryNew, connectionManagerFactoryNew);
    }

    public UserServiceImpl() {
        super();
    }

    @Override
    public void update(final User entityNew) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                connectionManager.disableAutoCommit();
                UserDao userDao =
                        getDaoFactory().createAccountDao(connectionManager);
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
                            getDaoFactory().createAccountDao(connectionManager);
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

    @Override
    public User findUserByEmail(final String email) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
            return userDao.findUserByEmail(email);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<User> findUsersByUsername(final String username,
                                          final int limit, final int offset)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
            return userDao.findUsersByUsername(username, limit, offset);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public User findUserByUsername(final String username) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
            return userDao.findUserByUsername(username);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public User authorize(final String login,
                          final String password)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
            User user = userDao.findUserByUsername(login);
            if (user != null && argon2.verify(user.getPassword(), password)) {
                user.setPassword(null);
                return user;
            }
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
        return null;
    }

    @Override
    public List<User> findUsersByRole(final Role role, final int limit,
                                      final int offset)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
            return userDao.findUsersByRole(role, limit, offset);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public User findById(final long id) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
            return userDao.findEntityById(id);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public void updateState(final long id) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                connectionManager.disableAutoCommit();
                UserDao userDao =
                        getDaoFactory().createAccountDao(connectionManager);
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

    @Override
    public void findLikedCubes(final User userNew, final int limit,
                               final int offset)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
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

    @Override
    public List<User> findAll(final int offset, final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
            return userDao.findAll(offset, limit);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public boolean addCubeToBasket(final User userNew, final long cubeId)
            throws ServiceException {
        boolean flag;
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                connectionManager.disableAutoCommit();
                UserDao userDao =
                        getDaoFactory().createAccountDao(connectionManager);
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

    @Override
    public RubiksCube findCubeFromBookmarks(final User userNew,
                                            final long cubeId)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
            return userDao.findCubeFromBookmarksById(userNew, cubeId);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public void removeFromBookmarks(final User userNew, final long id)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                connectionManager.disableAutoCommit();
                UserDao userDao =
                        getDaoFactory().createAccountDao(connectionManager);
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

    @Override
    public int findElementCount() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao accountDao =
                    getDaoFactory().createAccountDao(connectionManager);
            return accountDao.findElementCount();
        } catch (PersistentException e) {
            throw new ServiceException(e);
        }
    }

    private String argonTwoHashAlgorithm(final String newPassword) {
        final int iNew = 4;
        final int iNew1 = 1024;
        return argon2.hash(iNew, iNew1 * iNew1, iNew, newPassword);
    }
}
