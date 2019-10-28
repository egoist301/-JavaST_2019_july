package by.training.catalog.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UserValidatorTest {
    @DataProvider(name = "is valid parameters")
    private Object[][] isValid() {
        return new Object[][]{
                {"admin", "admin", "admin@mail.ru", "322-11-14"},
                {"administrator123", "admin", "admin@mail.ru", "322-11-14"},
                {"admin", "administrator123", "admin@mail.ru", "322-11-14"},
                {"admin", "admin", "admin@mail.comput", "322-11-14"}
        };
    }

    @DataProvider(name = "is invalid parameters")
    private Object[][] isInvalid() {
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

    @DataProvider(name = "Incorrect username")
    private Object[][] usernameIncorrectProvider() {
        return new Object[][]{
                {""},
                {null},
                {"dwadwadawdwadwadwadwa"}
        };
    }

    @DataProvider(name = "Incorrect password")
    private Object[][] passwordIncorrectProvider() {
        return new Object[][]{
                {""},
                {null},
                {"dwadwadawdwadwadwadwa"}
        };
    }

    @DataProvider(name = "Incorrect email")
    private Object[][] emailIncorrectProvider() {
        return new Object[][]{
                {""},
                {null},
                {"dwadwadaw"},
                {"wdwad@mail"},
                {"wadaw@mail.r"},
                {"wdawfa@mail.computer"}
        };
    }

    @DataProvider(name = "Incorrect phone")
    private Object[][] phoneIncorrectProvider() {
        return new Object[][]{
                {""},
                {null},
                {"3221114"},
                {"dwadwa"}
        };
    }


    @Test(dataProvider = "is valid parameters",
            dependsOnGroups = "validate user parameter")
    public void testIsValid(final String username, final String password,
                            final String email, final String phone) {
        boolean valid = UserValidator.isValid(username, password, email, phone);
        assertTrue(valid);
    }

    @Test(dataProvider = "is invalid parameters",
            dependsOnGroups = "validate user parameter")
    public void testIsInvalid(final String username,
                              final String password,
                              final String email, final String phone) {
        boolean valid = UserValidator.isValid(username, password, email, phone);
        assertFalse(valid);
    }

    @Test(dataProvider = "Incorrect username",
            groups = "validate user parameter")
    public void testInvalidateUsername(final String username) {
        boolean result = UserValidator.invalidateUsername(username);
        assertTrue(result);
    }

    @Test(groups = "validate user parameter")
    public void testValidateUsername() {
        boolean result = UserValidator.invalidateUsername("dwadwa");
        assertFalse(result);
    }

    @Test(dataProvider = "Incorrect password",
            groups = "validate user parameter")
    public void testInvalidatePassword(final String password) {
        boolean result = UserValidator.invalidatePassword(password);
        assertTrue(result);
    }

    @Test(groups = "validate user parameter")
    public void testValidatePassword() {
        boolean result = UserValidator.invalidatePassword("dwdwa");
        assertFalse(result);
    }

    @Test(dataProvider = "Incorrect email",
            groups = "validate user parameter")
    public void testInvalidateEmail(final String email) {
        boolean result = UserValidator.invalidateEmail(email);
        assertTrue(result);
    }

    @Test(groups = "validate user parameter")
    public void testValidateEmail() {
        boolean result = UserValidator.invalidateEmail("dwafw@mail.ru");
        assertFalse(result);
    }

    @Test(dataProvider = "Incorrect phone",
            groups = "validate user parameter")
    public void testInvalidatePhone(final String phone) {
        boolean result = UserValidator.invalidatePhone(phone);
        assertTrue(result);
    }

    @Test(groups = "validate user parameter")
    public void testValidatePhone() {
        boolean result = UserValidator.invalidatePhone("322-11-14");
        assertFalse(result);
    }
}