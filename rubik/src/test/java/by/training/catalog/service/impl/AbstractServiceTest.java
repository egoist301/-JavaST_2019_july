package by.training.catalog.service.impl;

import by.training.catalog.service.ServiceInitializer;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.Sources;
import com.wix.mysql.SqlScriptSource;
import com.wix.mysql.config.MysqldConfig;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_latest;

public abstract class AbstractServiceTest {
    protected static final String DATABASE = "rubik_test";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "";
    private static final int PORT = 3307;
    private static final SqlScriptSource[] SQL_SCRIPT_SOURCES
            = {classPathScript("sql/create_tables.sql"),
            classPathScript("sql/init_data.sql")};
    protected static EmbeddedMysql database;

    @BeforeSuite
    public void init() throws SQLException {
        MysqldConfig config = aMysqldConfig(v5_7_latest)
                .withPort(PORT)
                .withCharset(UTF8)
                .withUser(USERNAME, PASSWORD)
                .build();

        database = anEmbeddedMysql(config)
                .addSchema(DATABASE, SQL_SCRIPT_SOURCES)
                .start();
        ResourceBundle bundle = ResourceBundle.getBundle("database");
        ServiceInitializer.init(bundle, "/");
    }

    @BeforeTest
    public void initMethod() {
        database.reloadSchema(DATABASE, SQL_SCRIPT_SOURCES);
    }

    @AfterSuite
    public void destroy() {
        database.stop();
    }
}
