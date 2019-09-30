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
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.util.List;

public class UserServiceImpl extends AbstractService implements UserService {

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
                UserDao userDao =
                        getDaoFactory().createAccountDao(connectionManager);
                entityNew.setPassword(
                        argonTwoHashAlgorithm(entityNew.getPassword()));
                userDao.update(entityNew);
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
    public void create(final User entityNew) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            try {
                UserDao userDao =
                        getDaoFactory().createAccountDao(connectionManager);
                entityNew.setPassword(
                        argonTwoHashAlgorithm(entityNew.getPassword()));
                entityNew.setId(userDao.create(entityNew));
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
    public User findAccountByUsername(final String username)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
            return userDao.findAccountByUsername(username);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public User findAccountByEmail(final String email) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
            return userDao.findAccountByEmail(email);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public User findAccountByPhone(final int phone) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
            return userDao.findAccountByPhone(phone);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public User findAccountByLoginAndPassword(final String login,
                                              final String password)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
            return userDao.findAccountByLoginAndPassword(login,
                    argonTwoHashAlgorithm(password));
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public List<User> findAccountByRole(final Role role, final int limit,
                                        final int offset)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
            return userDao.findAccountByRole(role, limit, offset);
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
    public List<User> findAll() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
            return userDao.findAll();
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
    public void addCubeToBasket(final User userNew, final RubiksCube cubeNew)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
            userDao.addCubeToBasket(userNew, cubeNew);
        } catch (PersistentException eNew) {
            throw new ServiceException(eNew);
        }
    }

    @Override
    public void removeFromBasket(final User userNew, final RubiksCube cubeNew)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     getConnectionManagerFactory().createConnectionManager()) {
            UserDao userDao =
                    getDaoFactory().createAccountDao(connectionManager);
            userDao.removeCubeFromBasket(userNew, cubeNew);
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
        Argon2 argon2 =
                Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        final int iNew = 4;
        final int iNew1 = 1024;
        return argon2.hash(iNew, iNew1 * iNew1, iNew, newPassword);
    }
}
