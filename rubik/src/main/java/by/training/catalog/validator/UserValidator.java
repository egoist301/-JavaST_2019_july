package by.training.catalog.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * User validator parameters.
 */
public final class UserValidator {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Username regex.
     */
    private static final String USER_USERNAME_REGEX = "^[a-zA-Z0-9]{4,16}$";

    /**
     * Password regex.
     */
    private static final String USER_PASSWORD_REGEX = "^[a-zA-Z0-9]{4,16}$";
    /**
     * Email regex.
     */
    private static final String USER_EMAIL_REGEX =
            "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\"
                    + ".[a-z]{2,6}$";
    /**
     * Phone regex.
     */
    private static final String USER_MOBILE_REGEX =
            "([0-9]{3}-[0-9]{2}-[0-9]{2})";

    /**
     * Default constructor.
     */
    private UserValidator() {
    }

    /**
     * Check invalidate username.
     *
     * @param username username.
     * @return invalid or valid.
     */
    public static boolean invalidateUsername(final String username) {
        return username == null || !username.matches(USER_USERNAME_REGEX);
    }

    /**
     * Check invalidate password.
     *
     * @param password password.
     * @return invalid or valid.
     */
    public static boolean invalidatePassword(final String password) {
        return password == null || !password.matches(USER_PASSWORD_REGEX);
    }

    /**
     * Check invalidate email.
     *
     * @param email email.
     * @return invalid or valid.
     */
    public static boolean invalidateEmail(final String email) {
        return email == null || !email.matches(USER_EMAIL_REGEX);
    }

    /**
     * Check invalidate phone.
     *
     * @param phone phone.
     * @return invalid or valid.
     */
    public static boolean invalidatePhone(final String phone) {
        return phone == null || !phone.matches(USER_MOBILE_REGEX);
    }

    /**
     * Check valid parameters or not.
     *
     * @param username username.
     * @param password password.
     * @param email    email.
     * @param phone    phone.
     * @return true or false.
     */
    public static boolean isValid(final String username, final String password,
                                  final String email, final String phone) {
        boolean valid = true;
        if (invalidateUsername(username)) {
            LOGGER.warn("incorrect login: {}", username);
            valid = false;
        }
        if (invalidatePassword(password)) {
            LOGGER.warn("incorrect password: {}", password);
            valid = false;
        }
        if (invalidateEmail(email)) {
            LOGGER.warn("incorrect email: {}", email);
            valid = false;
        }
        if (invalidatePhone(phone)) {
            LOGGER.warn("incorrect phone: {}", phone);
            valid = false;
        }
        return valid;
    }
}
