package by.training.catalog.dao.pool;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Connection pool, which contains connections of ProxyConnection type.
 * Implemented as singleton.
 *
 * @see ProxyConnection
 */
public final class ConnectionPool {
    /**
     * Singleton. Instance.
     */
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Contains connections, which are available for use.
     */
    private ConcurrentLinkedQueue<ProxyConnection> pool
            = new ConcurrentLinkedQueue<>();
    /**
     * Contains connections, which are used and aren't available to take.
     */
    private Set<ProxyConnection> used = ConcurrentHashMap.newKeySet();
    /**
     * Locker.
     */
    private ReentrantLock locker = new ReentrantLock();
    /**
     * MySQL data source.
     */
    private MysqlDataSource source;
    /**
     * Max pool size.
     */
    private int maxPoolSize;
    /**
     * Number of connection.
     */
    private AtomicInteger connectionNumber;
    /**
     * Connection timeout.
     */
    private int checkConnectionTimeout;
    /**
     * Idle connections and used. For logger.
     */
    private static final String IDLE_AND_USED = "Idle connections: {}, used "
            + "connections: {}";
    /**
     * SQL state and message. For logger.
     */
    private static final String STATE_AND_MESSAGE = "SQL state: {}, SQL "
            + "message: {}.";


    /**
     * Default constructor.
     */
    private ConnectionPool() {
    }

    /**
     * Get-method to get pool instance.
     *
     * @return connection pool instance.
     */
    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    /**
     * Initialize a connection pool.
     * Can throw ConnectionPoolException.
     *
     * @param bundle resource bundle.
     * @see ConnectionPoolException
     */
    public void init(final ResourceBundle bundle) {
        try {
            LOGGER.info("Initializing connection pool.");
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
            LOGGER.info("Connection pool is successfully initialized."
                    + " Idle connections: {}", pool.size());
        } catch (SQLException | MissingResourceException
                | ClassNotFoundException | NumberFormatException e) {
            LOGGER.error("Connection pool initialization error. {}",
                    e.getMessage());
            throw new ConnectionPoolException(
                    "Cannot initialize connection pool.");
        }
    }

    /**
     * Get-method to get connection. Takes available connection
     * from container, if it has available connection, put this connection
     * to using connection container. If available
     * connections container hasn't any connection, tries to create new
     * connection. Then returns this connection.
     *
     * @return a connection
     */
    public Connection getConnection() {
        try {
            locker.lock();
            if (!pool.isEmpty()) {
                ProxyConnection connection;
                connection = pool.poll();
                if (connection.isClosed()) {
                    used.remove(connection);
                   return createConnection();
                }
                used.add(connection);
                LOGGER.debug("Got a connection from pool. "
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
            LOGGER.error("Cannot create new connection. "
                            + STATE_AND_MESSAGE,
                    e.getSQLState(), e.getMessage());
            throw new ConnectionPoolException("Cannot create new connection.");
        } finally {
            locker.unlock();
        }
    }

    /**
     * Receive a connection, check it and put into available
     * connections container.
     *
     * @param connection received connection.
     */
    void releaseConnection(final ProxyConnection connection) {
        try {
            if (connection.isValid(checkConnectionTimeout)) {
                connection.clearWarnings();
                used.remove(connection);
                pool.add(connection);
                LOGGER.debug("Connection released. "
                                + IDLE_AND_USED,
                        pool.size(), used.size());
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot release connection. Connection is invalid. "
                            + STATE_AND_MESSAGE,
                    e.getSQLState(), e.getMessage());
            try {
                connection.getConnection().close();
            } catch (SQLException e1) {
                LOGGER.error("Cannot close connection. "
                                + STATE_AND_MESSAGE,
                        e.getSQLState(), e.getMessage());
            }
        }
    }

    /**
     * Closes connections.
     */
    public void close() {
        pool.addAll(used);
        used.clear();
        for (ProxyConnection con : pool) {
            try {
                con.getConnection().close();
            } catch (SQLException e) {
                LOGGER.error("Cannot close connection. "
                                + STATE_AND_MESSAGE,
                        e.getSQLState(), e.getMessage());
            }
        }
    }

    /**
     * Creates connection and put it into available
     * connections container.
     *
     * @return connection.
     * @throws SQLException sql exception.
     */
    private ProxyConnection createConnection() throws SQLException {
        LOGGER.debug("Creating a connection.");
        ProxyConnection connection
                = new ProxyConnection(source.getConnection());
        used.add(connection);
        LOGGER.info("New connection is created. "
                        + IDLE_AND_USED,
                pool.size(), used.size());
        connectionNumber.incrementAndGet();
        return connection;
    }
}
