package by.training.catalog.dao.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHolder {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String FILE_NAME = "properties/prop.properties";
    private static final String DRIVER_FIELD = "driverName";
    private static final String USER_FIELD = "dbUser";
    private static final String PASSWORD_FIELD = "dbPassword";
    private static final String URL_FIELD = "URL";
    private static final String POOL_SIZE = "poolSize";
    private static final String INIT_COUNT = "initCount";

    /**
     * Contains a driver name
     */
    private final String driverName;
    /**
     * Contains a user name
     */
    private final String userName;
    /**
     * Contains a password
     */
    private final String password;
    /**
     * Contains a url
     */
    private final String url;
    /**
     * Contains a size of connection pool
     */
    private final int poolSize;
    /**
     * Contains an amount of connections to initialize firs time
     */
    private final int initCount;

    PropertyHolder() {
        Properties properties = new Properties();
        try (InputStream inputStream = PropertyHolder.class.getClassLoader()
                .getResourceAsStream(FILE_NAME)) {
            properties.load(inputStream);
            LOGGER.info("Properties was loaded");
            driverName = properties.getProperty(DRIVER_FIELD);
            userName = properties.getProperty(USER_FIELD);
            password = properties.getProperty(PASSWORD_FIELD);
            url = properties.getProperty(URL_FIELD);
            poolSize = Integer.parseInt(properties.getProperty(POOL_SIZE));
            initCount = Integer.parseInt(properties.getProperty(INIT_COUNT));
        } catch (IOException e) {
            LOGGER.fatal("Fatal error in reading file:", e);
            throw new ConnectionPoolException("Error in reading file: ", e);
        }
    }

    String getDriverName() {
        return driverName;
    }

    String getUserName() {
        return userName;
    }

    String getPassword() {
        return password;
    }

    String getUrl() {
        return url;
    }

    int getInitCount() {
        return initCount;
    }

    int getPoolSize() {
        return poolSize;
    }
}
