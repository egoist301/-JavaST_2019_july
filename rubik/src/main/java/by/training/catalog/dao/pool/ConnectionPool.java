package by.training.catalog.dao.pool;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private Logger logger = LogManager.getLogger(getClass());
    private BlockingQueue<ProxyConnection> pool
            = new LinkedBlockingQueue<>();
    private List<ProxyConnection> used
            = new LinkedList<>();
    private ReentrantLock locker = new ReentrantLock();
    private MysqlDataSource source;
    private int maxPoolSize;
    private AtomicInteger connectionNumber;
    private int checkConnectionTimeout;
    private static final String IDLE_AND_USED = "Idle connections: {}, used "
            + "connections: {}";
    private static final String STATE_AND_MESSAGE = "SQL state: {}, SQL "
            + "message: {}.";


    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public void init() {
        try {
            logger.info("Initializing connection pool.");
            ResourceBundle bundle = ResourceBundle.getBundle("database");
            String driver = bundle.getString("db.driver");
            String url = bundle.getString("db.url");
            String user = bundle.getString("db.user");
            String pass = bundle.getString("db.pass");
            Class.forName(driver);
            int initPoolSize = Integer
                    .parseInt(bundle.getString("db.init-pool-size"));
            maxPoolSize = Integer
                    .parseInt(bundle.getString("db.max-pool-size"));
            checkConnectionTimeout = Integer
                    .parseInt(bundle.getString("db.connection-timeout"));
            if (maxPoolSize < 1
                    || initPoolSize < 1
                    || initPoolSize > maxPoolSize) {
                throw new ConnectionPoolException("Invalid parameters"
                        + " for pool initialization");
            }
            source = new MysqlDataSource();
            source.setURL(url);
            source.setUser(user);
            source.setPassword(pass);
            for (int i = 0; i < initPoolSize; i++) {
                pool.add(new ProxyConnection(source.getConnection()));
            }
            connectionNumber = new AtomicInteger(initPoolSize);
            logger.info("Connection pool is successfully initialized."
                    + " Idle connections: {}", pool.size());
        } catch (SQLException | MissingResourceException
                | ClassNotFoundException | NumberFormatException e) {
            logger.error("Connection pool initialization error. {}",
                    e.getMessage());
            throw new ConnectionPoolException(
                    "Cannot initialize connection pool.");
        }
    }

    public ProxyConnection getConnection() {
        try {
            locker.lock();
            if (!pool.isEmpty()) {
                ProxyConnection connection;
                connection = pool.poll();
                used.add(connection);
                logger.debug("Got a connection from pool. "
                                + IDLE_AND_USED,
                        pool.size(), used.size());
                return connection;
            } else {
                if (connectionNumber.get() < maxPoolSize) {
                    return createConnection();
                }
                throw new ConnectionPoolException("No available connections.");
            }
        } catch (SQLException e) {
            logger.error("Cannot create new connection. "
                            + STATE_AND_MESSAGE,
                    e.getSQLState(), e.getMessage());
            throw new ConnectionPoolException("Cannot create new connection.");
        } finally {
            locker.unlock();
        }
    }

    void releaseConnection(final ProxyConnection connection) {
        try {
            if (connection.isValid(checkConnectionTimeout)) {
                connection.clearWarnings();
                used.remove(connection);
                pool.add(connection);
                logger.debug("Connection released. "
                                + IDLE_AND_USED,
                        pool.size(), used.size());
            }
        } catch (SQLException e) {
            logger.error("Cannot release connection. Connection is invalid. "
                            + STATE_AND_MESSAGE,
                    e.getSQLState(), e.getMessage());
            try {
                connection.getConnection().close();
            } catch (SQLException e1) {
                logger.error("Cannot close connection. "
                                + STATE_AND_MESSAGE,
                        e.getSQLState(), e.getMessage());
            }
        }
    }

    public void close() {
        pool.addAll(used);
        used.clear();
        for (ProxyConnection con : pool) {
            try {
                con.getConnection().close();
            } catch (SQLException e) {
                logger.error("Cannot close connection. "
                                + STATE_AND_MESSAGE,
                        e.getSQLState(), e.getMessage());
            }
        }
    }

    private ProxyConnection createConnection() throws SQLException {
        logger.debug("Creating a connection.");
        ProxyConnection connection
                = new ProxyConnection(source.getConnection());
        used.add(connection);
        logger.info("New connection is created. "
                        + IDLE_AND_USED,
                pool.size(), used.size());
        connectionNumber.incrementAndGet();
        return connection;
    }
}
