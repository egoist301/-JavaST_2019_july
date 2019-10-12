package by.training.catalog.service;

import by.training.catalog.dao.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServiceInitializer {
    private ServiceInitializer() {
    }

    public static void init() {

        ConnectionPool.getInstance().init();

    }
}
