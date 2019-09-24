package by.training.catalog.dao.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.List;


public final class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private BlockingQueue<ProxyConnection> availableConnections;
    /**
     * Contains connections, which are used and aren't available to take.
     */
    private List<ProxyConnection> usingConnections;
    /**
     * PropertyHolder instance. Used to get properties to connect.
     * with database
     *
     * @see PropertyHolder
     */
    private PropertyHolder propertyHolder; //TODO read from property file.

    /**
     * Construct a connection pool.
     * Initialize a pool.
     */
    private ConnectionPool() {
        propertyHolder = new PropertyHolder();
        init();
    }

    /**
     * Initialize a connection pool
     * Can throw ConnectionPoolException
     *
     * @see ConnectionPoolException
     */
    private void init() {
        availableConnections =
                new LinkedBlockingQueue<>(propertyHolder.getPoolSize());
        usingConnections = new LinkedList<>();
        for (int i = 0; i < propertyHolder.getPoolSize(); i++) {
            createConnection();
        }
        LOGGER.debug("ProxyConnectionPool was created");
        if (availableConnections.isEmpty()) {
            LOGGER.fatal(
                    "Fatal error in initializer: connection pool hasn't any "
                            + "connections");
            throw new ConnectionPoolException(
                    "Connection pool hasn't any connections");
        }
    }

    /**
     * Inner instance-holder class.
     */
    private static class ConnectionPoolHolder {
        /**
         * Hold the instance of connection pool.
         */
        private static final ConnectionPool POOL = new ConnectionPool();
    }

    /**
     * Get-method to get pool instance.
     *
     * @return connection pool instance.
     */
    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.POOL;
    }

    /**
     * Creates connection and put it into available.
     * connections container.
     * Can throw ConnectionPoolException.
     *
     * @see ConnectionPoolException
     */
    private void createConnection() {
        try {
            Connection connection = DriverManager
                    .getConnection(propertyHolder.getUrl(),
                            propertyHolder.getProp());
            availableConnections.offer(new ProxyConnection(connection));
            LOGGER.debug("New connection was offered to available");
        } catch (SQLException e) {
            LOGGER.error("Error in initializing connection: ", e);
            throw new ConnectionPoolException(e);
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
        ProxyConnection connection = null;
        try {
            if (availableConnections.isEmpty()
                    && usingConnections.size() < propertyHolder.getPoolSize()) {
                LOGGER.debug("A lack of connections");
                new Thread(this::createConnection).start();
            }
            connection = availableConnections.take();
            usingConnections.add(connection);
        } catch (InterruptedException e) {
            LOGGER.error("Error in taking connection from pool: ", e);
            Thread.currentThread().interrupt();
        }
        LOGGER.debug("User get a connection");
        return connection;
    }

    /**
     * Receive a connection, check it and put into available
     * connections container
     *
     * @param connection received connection
     * @return result of returning. True if completes successfully, false - if not
     */
    public boolean returnConnection(Connection connection) {
        boolean flag = false;
        if (connection instanceof ProxyConnection) {
            usingConnections.remove(connection);
            flag = availableConnections.offer((ProxyConnection) connection);
            LOGGER.debug("User returned connection to available");
        }
        return flag;
    }

    /**
     * Deregisters database drivers
     */
    private void deregisterDriver() {
        Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
        java.sql.Driver d = null;
        while (drivers.hasMoreElements()) {
            try {
                d = drivers.nextElement();
                DriverManager.deregisterDriver(d);
                LOGGER.debug(String.format("Driver %s deregistered", d));
            } catch (SQLException ex) {
                LOGGER.error(String.format("Error deregister driver %s", d),
                        ex);
            }
        }
    }

    /**
     * Closes connections and deregisters drivers
     */
    public void closePool() {
        for (int i = 0; i < availableConnections.size(); ) {
            try {
                availableConnections.take().close();
                LOGGER.debug("Connection was closed");
            } catch (InterruptedException | SQLException e) {
                LOGGER.error("Error in closing connection: ", e);
            }
        }
        deregisterDriver();
    }
}
