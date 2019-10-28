package by.training.catalog.service.impl;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.StoreImageService;
import com.wix.mysql.SqlScriptSource;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.wix.mysql.ScriptResolver.classPathScript;
import static org.testng.Assert.*;

public class StoreImageServiceImplTest extends AbstractServiceTest {
    private StoreImageService storeImageService;
    private static final SqlScriptSource[] SQL_SCRIPT_SOURCES
            = {classPathScript("sql/fill_user_table.sql")};

    @BeforeTest
    public void setUp() {
        ServiceFactory serviceFactory = new ServiceFactory();
        storeImageService = serviceFactory.createStoreImageService();
        database.executeScripts(DATABASE, SQL_SCRIPT_SOURCES);
    }

    @Test
    public void testFindImagesByRubikCorrect() throws ServiceException {
        RubiksCube cube = new RubiksCube(1);
        storeImageService.findImagesByRubik(cube);
        assertNotNull(cube.getPaths());
    }

    @Test
    public void testFindImagesByRubikIncorrect() throws ServiceException {
        RubiksCube cubeNew = new RubiksCube(0);
        storeImageService.findImagesByRubik(cubeNew);
        assertEquals(cubeNew.getPaths().size(), 0);
    }
}