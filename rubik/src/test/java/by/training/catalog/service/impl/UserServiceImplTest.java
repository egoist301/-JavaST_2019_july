package by.training.catalog.service.impl;

import by.training.catalog.bean.Role;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.User;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import com.wix.mysql.SqlScriptSource;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.wix.mysql.ScriptResolver.classPathScript;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

public class UserServiceImplTest extends AbstractServiceTest {
    private UserService service;
    private static final SqlScriptSource[] SQL_SCRIPT_SOURCES
            = {classPathScript("sql/fill_user_table.sql")};

    @BeforeTest
    public void setUp() {
        ServiceFactory serviceFactory = new ServiceFactory();
        service = serviceFactory.createUserService();
        database.executeScripts(DATABASE, SQL_SCRIPT_SOURCES);
    }

    @DataProvider(name = "Correct authorize")
    private Object[][] authorizeCorrectProvider() {
        return new Object[][]{
                {"admin", "admin"},
                {"user05", "bann"}
        };
    }

    @DataProvider(name = "Incorrect authorize")
    private Object[][] authorizeIncorrectProvider() {
        return new Object[][]{
                {null, "admin"},
                {"admin", null},
                {"ad", "admin"},
                {"admin", "ad"},
                {"", "admin"},
                {"admin", ""}
        };
    }

    @DataProvider(name = "Correct find cube from bookmarks")
    private Object[][] findCubeFromBookmarksCorrectProvider() {
        return new Object[][]{
                {new User(1), 1},
                {new User(1), 3},
                {new User(1), 5},
                {new User(2), 1}

        };
    }

    @DataProvider(name = "Incorrect find cube from bookmarks")
    private Object[][] findCubeFromBookmarksIncorrectProvider() {
        return new Object[][]{
                {null, 1},
                {new User(1), 4},
                {new User(3), 5},
                {new User(2), 3}

        };
    }

    @DataProvider(name = "Correct create user")
    private Object[][] createCorrectProvider() {
        return new Object[][]{
                {"zhenya", "filip@mail.ru", "322-11-14", "zhenya"},
                {"sasha", "zalikjj@mail.ru", "544-19-39", "123456"},
                {"ader", "ader@mail.ru", "544-19-39", "123456"}
        };
    }

    @DataProvider(name = "Incorrect create user")
    private Object[][] createIncorrectProvider() {
        return new Object[][]{
                {null, "dawd@mail.ru", "322-11-14", "zhenya"},
                {"nu", "dawd@mail.ru", "322-11-14", "zhenya"},
                {"zhenyag", "dawd", "322-11-14", "zhenya"},
                {"zhenyad", null, "322-11-14", "zhenya"},
                {"admin", "dawd@mail.ru", "322-11-14", "zhenya"},
                {"dwdadw", "admin@mail.ru", "322-11-14", "zhenya"},
                {"null", "dawd@mail.ru", "32-11-14", "zhenya"},
                {"null", "dawd@mail.ru", null, "zhenya"},
                {"null", "dawd@mail.ru", "322-11-14", null},
                {"null", "dawd@mail.ru", "32-11-14", "zhe"},
                {"nulldawdwadawdawdwa", "dawd@mail.ru", "32-11-14", "zhenya"},
                {"null", "dawd@mail.ru", "332-11-14",
                        "zhenyadwadawdwadwdawdwa"},

        };
    }

    @DataProvider(name = "Correct find count of rubik's")
    private Object[][] findCountRubiksCorrectProvider() {
        return new Object[][]{
                {1, 3},
                {2, 1}
        };
    }

    @DataProvider(name = "Incorrect find count of rubik's")
    private Object[][] findCountRubiksIncorrectProvider() {
        return new Object[][]{
                {6, 0}
        };
    }

    @DataProvider(name = "Correct find user by email")
    private Object[][] findUserByEmailCorrectProvider() {
        return new Object[][]{
                {"admin@mail.ru", "admin"},
                {"user05@mail.ru", "user05"}
        };
    }

    @DataProvider(name = "Incorrect find user by email")
    private Object[][] findUserByEmailIncorrectProvider() {
        return new Object[][]{
                {null, null},
                {"dwadadw", null},
                {"dwdawd@maidawd", null}
        };
    }

    @DataProvider(name = "Correct find user by username")
    private Object[][] findUserByUsernameCorrectProvider() {
        return new Object[][]{
                {"admin"},
                {"user05"}
        };
    }

    @DataProvider(name = "Incorrect find user by username")
    private Object[][] findUserByUsernameIncorrectProvider() {
        return new Object[][]{
                {null},
                {"dwa"}
        };
    }

    @DataProvider(name = "Correct update state")
    private Object[][] updateStateCorrectProvider() {
        return new Object[][]{
                {2}
        };
    }

    @DataProvider(name = "Incorrect update state")
    private Object[][] updateStateIncorrectProvider() {
        return new Object[][]{
                {6},
                {7}
        };
    }

    @DataProvider(name = "Correct find by id")
    private Object[][] findByIdCorrectProvider() {
        return new Object[][]{
                {1},
                {2}
        };
    }

    @DataProvider(name = "Incorrect find by id")
    private Object[][] findByIdIncorrectProvider() {
        return new Object[][]{
                {6},
                {7},
        };
    }

    @DataProvider(name = "Correct find liked cubes")
    private Object[][] findLikedCubesCorrectProvider() {
        return new Object[][]{
                {new User(1), 1, 0, 1},
                {new User(1), 2, 0, 2},
                {new User(1), 3, 0, 3},
                {new User(1), 3, 2, 1},
                {new User(2), 10, 0, 1},
                {new User(1), 0, 3, 0}
        };
    }

    @DataProvider(name = "Incorrect find liked cubes")
    private Object[][] findLikedCubesIncorrectProvider() {
        return new Object[][]{
                {null, 1, 1, 1},
                {new User(6), 1, 1, 1},
                {new User(1), -1, 1, 1},
                {new User(1), 1, -1, 1}
        };
    }

    @DataProvider(name = "Correct update")
    private Object[][] updateCorrectProvider() {
        return new Object[][]{
                {new User(1, "admin", "admin")},
                {new User(1, null, "admin")}
        };
    }

    @DataProvider(name = "Incorrect update")
    private Object[][] updateIncorrectProvider() {
        return new Object[][]{
                {null},
                {new User(6, "admin", "admin")},
                {new User(-1, "admin", "admin")},
                {new User(1, "admin", (String) null)}
        };
    }

    @DataProvider(name = "Correct find users by username")
    private Object[][] findUsersByUsernameCorrectProvider() {
        return new Object[][]{
                {"admin", 1, 0, 1},
                {"a", 1, 0, 1},
                {"ad", 1, 0, 1},
                {"ad", 2, 0, 2},
                {"admin", 2, 1, 0},
                {"admin", 3, 0, 1},
                {"admin", 0, 1, 0},
                {"a", 0, 1, 0},
                {"", 4, 0, 4},
                {"", 4, 3, 2}
        };
    }

    @DataProvider(name = "Incorrect find users by username")
    private Object[][] findUsersByUsernameIncorrectProvider() {
        return new Object[][]{
                {null, 4, 0, 0},
                {"dw", -1, 1, 0},
                {"daw", 1, -1, 1}
        };
    }

    @DataProvider(name = "Correct find users by role")
    private Object[][] findUsersByRoleCorrectProvider() {
        return new Object[][]{
                {Role.ADMIN, 1, 0, 1},
                {Role.ADMIN, 1, 1, 0},
                {Role.USER, 1, 0, 1},
                {Role.USER, 4, 0, 4},
                {Role.USER, 2, 2, 2},
                {Role.USER, 4, 1, 3}
        };
    }

    @DataProvider(name = "Incorrect find users by role")
    private Object[][] findUsersByRoleIncorrectProvider() {
        return new Object[][]{
                {null, 1, 0, 0},
                {Role.USER, -1, 0, 0},
                {Role.USER, 0, -1, 0}
        };
    }

    @DataProvider(name = "Correct find all")
    private Object[][] findAllCorrectProvider() {
        return new Object[][]{
                {0, 5, 5},
                {1, 3, 3},
                {3, 2, 2},
                {5, 1, 0},
                {1, 0, 0}
        };
    }

    @DataProvider(name = "Incorrect find all")
    private Object[][] findAllIncorrectProvider() {
        return new Object[][]{
                {-1, 3, 3},
                {0, -1, 2}
        };
    }

    @DataProvider(name = "Correct add cube")
    private Object[][] addCubeCorrectProvider() {
        return new Object[][]{
                {new User(1), 6},
                {new User(2), 7}
        };
    }

    @DataProvider(name = "Incorrect add cube")
    private Object[][] addCubeIncorrectProvider() {
        return new Object[][]{
                {null, 1},
                {new User(8), 5},
                {new User(1), -1},
                {new User(1), 100}
        };
    }

    @DataProvider(name = "Correct remove cube")
    private Object[][] removeCubeCorrectProvider() {
        return new Object[][]{
                {new User(1), 6, 3},
                {new User(2), 7, 1}
        };
    }

    @DataProvider(name = "Incorrect remove cube")
    private Object[][] removeCubeIncorrectProvider() {
        return new Object[][]{
                {null, 1, 1},
                {new User(14), 1, 1}
        };
    }

    @Test(dataProvider = "Correct update")
    public void testUpdateCorrect(final User userNew) throws ServiceException {
        service.update(userNew);
        assertNull(userNew.getPassword());
    }

    @Test(dataProvider = "Incorrect update", expectedExceptions =
            ServiceException.class)
    public void testUpdateIncorrect(final User userNew)
            throws ServiceException {
        service.update(userNew);
        assertNull(userNew.getPassword());
    }

    @Test(dataProvider = "Correct find count of rubik's")
    public void testFindCountRubiksCorrect(final long id, final int expected)
            throws ServiceException {
        int actual = service.findCountRubiks(id);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Incorrect find count of rubik's",
            expectedExceptions = ServiceException.class)
    public void testFindCountRubiksIncorrect(final long id, final int expected)
            throws ServiceException {
        int actual = service.findCountRubiks(id);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Correct create user")
    public void testCreateCorrect(final String username, final String email,
                                  final String phone, final String password)
            throws ServiceException {
        boolean expected = service.create(username, email, phone, password);
        assertTrue(expected);
    }

    @Test(dataProvider = "Incorrect create user")
    public void testCreateIncorrect(final String username, final String email,
                                    final String phone, final String password)
            throws ServiceException {
        boolean expected = service.create(username, email, phone, password);
        assertFalse(expected);
    }

    @Test(dataProvider = "Correct find user by email", dependsOnMethods =
            {"testFindUserByUsernameCorrect",
                    "testFindUserByUsernameIncorrect"})
    public void testFindUserByEmailCorrect(final String email,
                                           final String username)
            throws ServiceException {
        User expected = service.findUserByUsername(username);
        User actual = service.findUserByEmail(email);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Incorrect find user by email", dependsOnMethods =
            {"testFindUserByUsernameCorrect",
                    "testFindUserByUsernameIncorrect"})
    public void testFindUserByEmailIncorrect(final String email,
                                             final String username)
            throws ServiceException {
        User expected = service.findUserByUsername(username);
        User actual = service.findUserByEmail(email);
        assertEquals(actual, expected);
    }


    @Test(dataProvider = "Correct find users by username", dependsOnMethods =
            {"testCreateCorrect"})
    public void testFindUsersByUsernameCorrect(final String username,
                                               final int limit,
                                               final int offset,
                                               final int expected)
            throws ServiceException {
        int actual =
                service.findUsersByUsername(username, limit, offset).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Incorrect find users by username",
            dependsOnMethods = {"testCreateIncorrect"}, expectedExceptions =
            ServiceException.class)
    public void testFindUsersByUsernameIncorrect(final String username,
                                                 final int limit,
                                                 final int offset,
                                                 final int expected)
            throws ServiceException {
        int actual =
                service.findUsersByUsername(username, limit, offset).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Correct find user by username")
    public void testFindUserByUsernameCorrect(final String username)
            throws ServiceException {
        User user = service.findUserByUsername(username);
        assertEquals(user.getUsername(), username);
    }

    @Test(dataProvider = "Incorrect find user by username")
    public void testFindUserByUsernameIncorrect(final String username)
            throws ServiceException {
        User user = service.findUserByUsername(username);
        assertNull(user);
    }

    @Test(dataProvider = "Correct authorize")
    public void testAuthorizeCorrect(final String username,
                                     final String password)
            throws ServiceException {
        User user = service.authorize(username, password);
        assertEquals(user.getUsername(), username);
    }

    @Test(dataProvider = "Incorrect authorize")
    public void testAuthorizeIncorrect(final String username,
                                       final String password)
            throws ServiceException {
        User user = service.authorize(username, password);
        assertNull(user);
    }

    @Test(dataProvider = "Correct find users by role", dependsOnMethods = {
            "testCreateCorrect"})
    public void testFindUsersByRoleCorrect(final Role role, final int limit,
                                           final int offset, final int expected)
            throws ServiceException {
        int actual = service.findUsersByRole(role, limit, offset).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Incorrect find users by role", expectedExceptions =
            ServiceException.class)
    public void testFindUsersByRoleIncorrect(final Role role, final int limit,
                                             final int offset,
                                             final int expected)
            throws ServiceException {
        int actual = service.findUsersByRole(role, limit, offset).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Correct find by id")
    public void testFindByIdCorrect(final long id) throws ServiceException {
        User user = service.findById(id);
        assertEquals(user.getId(), id);
    }

    @Test(dataProvider = "Incorrect find by id")
    public void testFindByIdIncorrect(final long id) throws ServiceException {
        User user = service.findById(id);
        assertNull(user);
    }

    @Test(dataProvider = "Correct update state", dependsOnMethods = {
            "testFindByIdCorrect", "testFindByIdIncorrect"})
    public void testUpdateStateCorrect(final long id) throws ServiceException {
        service.updateState(id);
        User user = service.findById(id);
        assertTrue(user.isBlocked());
    }

    @Test(dataProvider = "Incorrect update state", expectedExceptions =
            ServiceException.class, dependsOnMethods = {
            "testFindByIdCorrect", "testFindByIdIncorrect"})
    public void testUpdateStateIncorrect(final long id)
            throws ServiceException {
        service.updateState(id);
        User user = service.findById(id);
        assertFalse(user.isBlocked());
    }

    @Test(dataProvider = "Correct find liked cubes")
    public void testFindLikedCubesCorrect(final User userNew,
                                          final int limit, final int offset,
                                          final int expected)
            throws ServiceException {
        service.findLikedCubes(userNew, limit, offset);
        int actual = userNew.getCubes().size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Incorrect find liked cubes", expectedExceptions =
            ServiceException.class)
    public void testFindLikedCubesIncorrect(final User userNew,
                                            final int limit, final int offset,
                                            final int expected)
            throws ServiceException {
        service.findLikedCubes(userNew, limit, offset);
        int actual = userNew.getCubes().size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Correct find all", dependsOnMethods = {
            "testCreateCorrect"})
    public void testFindAllCorrect(final int offset, final int limit,
                                   final int expected) throws ServiceException {
        int actual = service.findAll(offset, limit).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Incorrect find all", dependsOnMethods = {
            "testCreateCorrect"}, expectedExceptions = ServiceException.class)
    public void testFindAllIncorrect(final int offset, final int limit,
                                     final int expected)
            throws ServiceException {
        int actual = service.findAll(offset, limit).size();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Correct add cube", dependsOnMethods = {
            "testFindCountRubiksCorrect", "testFindCountRubiksIncorrect"})
    public void testAddCubeToBookmarksCorrect(final User userNew,
                                              final long id)
            throws ServiceException {
        boolean result = service.addCubeToBookmarks(userNew, id);
        assertTrue(result);
    }

    @Test(dataProvider = "Incorrect add cube", dependsOnMethods = {
            "testFindCountRubiksCorrect", "testFindCountRubiksIncorrect"},
            expectedExceptions = ServiceException.class)
    public void testAddCubeToBookmarksIncorrect(final User userNew,
                                                final long id)
            throws ServiceException {
        boolean result = service.addCubeToBookmarks(userNew, id);
        assertTrue(result);
    }

    @Test(dependsOnMethods = {"testFindCountRubiksCorrect",
            "testFindCountRubiksIncorrect"})
    public void testAddCubeToBookmarksIncorrectWithoutException()
            throws ServiceException {
        User user = new User(1);
        long cubeId = 5;
        boolean result = service.addCubeToBookmarks(user, cubeId);
        assertFalse(result);
    }

    @Test(dataProvider = "Correct find cube from bookmarks")
    public void testFindCubeFromBookmarksCorrect(final User userNew,
                                                 final long cubeId)
            throws ServiceException {
        RubiksCube cube = service.findCubeFromBookmarks(userNew, cubeId);
        assertEquals(cube.getId(), cubeId);
    }

    @Test(dataProvider = "Incorrect find cube from bookmarks")
    public void testFindCubeFromBookmarksIncorrect(final User userNew,
                                                   final long cubeId)
            throws ServiceException {
        RubiksCube cube = service.findCubeFromBookmarks(userNew, cubeId);
        assertNull(cube);
    }

    @Test(dataProvider = "Correct remove cube", dependsOnMethods = {
            "testAddCubeToBookmarksCorrect", "testAddCubeToBookmarksIncorrect",
            "testFindCountRubiksCorrect"})
    public void testRemoveFromBookmarksCorrect(final User userNew,
                                               final long cubeId,
                                               final int expected)
            throws ServiceException {
        service.removeFromBookmarks(userNew, cubeId);
        int actual = service.findCountRubiks(userNew.getId());
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Incorrect remove cube", dependsOnMethods = {
            "testAddCubeToBookmarksCorrect", "testAddCubeToBookmarksIncorrect",
            "testFindCountRubiksCorrect"}, expectedExceptions =
            ServiceException.class)
    public void testRemoveFromBookmarksIncorrect(final User userNew,
                                                 final long cubeId,
                                                 final int expected)
            throws ServiceException {
        service.removeFromBookmarks(userNew, cubeId);
        int actual = service.findCountRubiks(userNew.getId());
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = {"testCreateCorrect"})
    public void testFindElementCount() throws ServiceException {
        int actual = service.findElementCount();
        int expected = 5;
        assertEquals(actual, expected);
    }
}