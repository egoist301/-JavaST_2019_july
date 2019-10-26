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
        if (username == null || !username.matches(USER_USERNAME_REGEX)) {
            LOGGER.warn("incorrect login: {}", username);
            valid = false;
        }
        if (password == null || !password.matches(USER_PASSWORD_REGEX)) {
            LOGGER.warn("incorrect password: {}", password);
            valid = false;
        }
        if (email == null || !email.matches(USER_EMAIL_REGEX)) {
            LOGGER.warn("incorrect email: {}", email);
            valid = false;
        }
        if (phone == null || !phone.matches(USER_MOBILE_REGEX)) {
            LOGGER.warn("incorrect phone: {}", phone);
            valid = false;
        }
        return valid;
    }
}
