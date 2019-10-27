package by.training.catalog.service.factory;

import by.training.catalog.bean.User;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class UserFactoryTest {
    private UserFactory userFactory;

    @BeforeTest
    void init() {
        userFactory = new UserFactory();
    }

    @DataProvider(name = "incorrect user parameters")
    private Object[][] createUserIncorrect() {
        return new Object[][]{
                {null, "admin", "admin@mail.ru", "322-11-14"},
                {"administrator123", null, "admin@mail.ru", "322-11-14"},
                {"admin", "administrator123", null, "322-11-14"},
                {"admin", "administrator12345", "admin@mail.comput", null},
                {"admin", "admin", "admin@mail", "322-22-11"},
                {"adminfwafawfawfwa", "admin", "admin@mail.ru", "322-22-11"},
                {"admin", "admin", "admin", "322-22-11"},
                {"flex", "flex", "flex@flex.flex", "21-212-21"},
                {"nsjenya", "nsdiana", "nsteam@nsmail.ns", "3122222"}
        };
    }

    @Test
    public void testCreateUserCorrect() {
        User user = userFactory.createUser("user", "user@mail.ru", "322-11-14",
                "user");
        assertNotNull(user);
    }

    @Test(dataProvider = "incorrect user parameters")
    public void testCreateUserIncorrect(final String username,
                                        final String password,
                                        final String email,
                                        final String phone) {
        User user = userFactory.createUser(username, email, phone, password);
        assertNull(user);
    }
}