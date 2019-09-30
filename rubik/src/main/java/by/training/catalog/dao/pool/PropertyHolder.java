package by.training.catalog.dao.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

class PropertyHolder {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String URL_FIELD = "url";
    private static final String POOL_SIZE = "size";

    /**
     * Contains a url.
     */
    private final String url;
    /**
     * Contains a size of connection pool.
     */
    private final int poolSize;
    private Properties prop;

    PropertyHolder() {
        prop = new Properties();
        prop.put(URL_FIELD,
                "jdbc:mysql://localhost:3306/rubik?serverTimezone=UTC");
        prop.put("user", "root");
        prop.put("password", "root");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        prop.put("useSSL", "false");
        prop.put(POOL_SIZE, "3");
        LOGGER.info("Properties was loaded");
        url = prop.getProperty(URL_FIELD);
        poolSize = Integer.parseInt(prop.getProperty(POOL_SIZE));
    }

    String getUrl() {
        return url;
    }

    public Properties getProp() {
        return prop;
    }

    int getPoolSize() {
        return poolSize;
    }
}
